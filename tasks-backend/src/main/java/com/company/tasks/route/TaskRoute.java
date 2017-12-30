package com.company.tasks.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel route that calls the TaskService backend.
 */
public class TaskRoute extends RouteBuilder {

    public static final String TASK_SERVICE_BEAN = "taskService";

    @Override
    public void configure() throws Exception {
        from("direct:getTask").routeId("getTask")
            .bean(TASK_SERVICE_BEAN, "getTask");

        from("direct:updateTask").routeId("updateTask")
            .bean(TASK_SERVICE_BEAN, "updateTask");

        from("direct:createTask").routeId("createTask")
            .bean(TASK_SERVICE_BEAN, "createTask");

        from("direct:deleteTask").routeId("deleteTask")
            .bean(TASK_SERVICE_BEAN, "deleteTask");
    }
}
