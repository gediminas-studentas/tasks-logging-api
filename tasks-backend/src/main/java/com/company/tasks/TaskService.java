package com.company.tasks;

import com.company.tasks.entity.TaskDTO;

/**
 * An pure Java interface that defines the services for the TaskDTO
 */
public interface TaskService {

    TaskDTO getTask(int taskId);

    void updateTask(TaskDTO task);

    Integer createTask(TaskDTO task);

    void deleteTask(int taskId);

}
