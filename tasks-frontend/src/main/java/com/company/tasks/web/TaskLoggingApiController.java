package com.company.tasks.web;

import com.company.tasks.entity.TaskDTO;
import com.company.tasks.exception.validator.TaskValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

@Path("/tasks/")
@Produces("application/json")
@Controller
@Api(value = "Tasks logging API", produces = "application/json")
public class TaskLoggingApiController {

    private final ProducerTemplate producer;

    public TaskLoggingApiController(ProducerTemplate producer) {
        this.producer = producer;
    }

    /**
     * The GET task by id operation
     */
    @GET
    @Path("/{taskId}")
    @ApiOperation(
            value = "Get task by providing task id",
            response = TaskDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource found"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    public Response getOrder(@PathParam("taskId") int taskId) {
        TaskDTO task = producer.requestBody("direct:getTask", taskId, TaskDTO.class);
        if (task != null) {
            return Response.ok(task).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * The PUT update task operation
     */
    @PUT
    public Response updateTask(TaskDTO task) {
        validationExceptionAwareCamelExecutor(() -> {
            producer.sendBody("direct:updateTask", task);
            return null;
        });

        return Response.ok().build();
    }

    /**
     * The POST create task operation
     */
    @POST
    public Response createTask(TaskDTO task) {
        String id = validationExceptionAwareCamelExecutor(
                () -> producer.requestBody("direct:createTask", task, String.class)
        );
        return Response.ok(id).build();
    }

    /**
     * The DELETE delete task operation
     */
    @DELETE
    @Path("/{taskId}")
    public Response deleteTask(@PathParam("taskId") int taskId) {
        producer.sendBody("direct:deleteTask", taskId);
        return Response.ok().build();
    }

    private static <T> T validationExceptionAwareCamelExecutor(Callable<T> callable) {
        try {
            return callable.call();
        } catch (CamelExecutionException e) {
            if (e.getCause() instanceof TaskValidationException) {
                throw new BadRequestException(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
