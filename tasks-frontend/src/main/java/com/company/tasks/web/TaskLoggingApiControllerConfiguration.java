package com.company.tasks.web;

import com.company.tasks.config.TaskServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TaskServiceConfiguration.class)
public class TaskLoggingApiControllerConfiguration {
}
