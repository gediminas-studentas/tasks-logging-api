package com.company.tasks.config;

import com.company.tasks.TaskService;
import com.company.tasks.TaskServiceImpl;
import com.company.tasks.persistent.config.PersistenceConfiguration;
import com.company.tasks.persistent.repository.TaskMappingRepository;
import com.company.tasks.persistent.repository.TaskRepository;
import com.company.tasks.route.TaskRoute;
import com.company.tasks.validator.TaskValidatorImpl;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
public class TaskServiceConfiguration {

    @Bean(name = TaskRoute.TASK_SERVICE_BEAN)
    public TaskService taskService(TaskRepository taskRepository, TaskMappingRepository taskMappingRepository) {
        return new TaskServiceImpl(taskRepository, taskMappingRepository, new TaskValidatorImpl());
    }

    @Bean
    public RouteBuilder taskRoute() {
        return new TaskRoute();
    }

}
