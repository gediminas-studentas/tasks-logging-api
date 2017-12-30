package com.company.tasks.entity.factory;

import com.company.tasks.entity.TaskDTO;
import com.company.tasks.exception.SubTaskNotFoundException;
import com.company.tasks.persistent.entity.Task;

import java.util.stream.Collectors;

public class TaskFactory {

    private final TaskByIdSupplier taskByIdSupplier;

    public TaskFactory(TaskByIdSupplier taskByIdSupplier) {
        this.taskByIdSupplier = taskByIdSupplier;
    }

    public Task buildFromTaskDTO(TaskDTO task) throws SubTaskNotFoundException {
        return Task.builder()
                .withId(task.getId())
                .withName(task.getName())
                .withDurationSpentInSeconds(task.getDurationSpentInSeconds())
                .withGroupName(task.getGroupName())
                .withAssignee(task.getAssignee())
                .withSubTasks(task.getSubTaskIds().stream().map(this::getSubTask).collect(Collectors.toList()))
                .withFinished(task.getFinished())
                .build();
    }

    private Task getSubTask(Integer taskId) throws SubTaskNotFoundException {
        return taskByIdSupplier.get(taskId).orElseThrow(
                () -> new SubTaskNotFoundException(String.format("Task by id %d is not found", taskId)));
    }

    
}
