package com.company.tasks.entity.factory;

import com.company.tasks.entity.TaskDTO;
import com.company.tasks.persistent.entity.Task;

import java.util.stream.Collectors;

public class TaskDTOFactory {

    public static TaskDTO buildFromTask(Task task) {
        return TaskDTO.builder()
                .withId(task.getId())
                .withName(task.getName())
                .withDurationSpentInSeconds(task.getDurationSpentInSeconds())
                .withGroupName(task.getGroupName())
                .withAssignee(task.getAssignee())
                .withSubTaskIds(task.getSubTasks().stream().map(Task::getId).collect(Collectors.toList()))
                .withFinished(task.getFinished())
                .build();
    }

    
}
