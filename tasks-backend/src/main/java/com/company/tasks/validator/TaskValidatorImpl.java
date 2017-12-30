package com.company.tasks.validator;

import com.company.tasks.exception.validator.NestedSubTasksException;
import com.company.tasks.exception.validator.NotAllSubTasksFinishedException;
import com.company.tasks.exception.validator.TaskValidationException;
import com.company.tasks.persistent.entity.Task;

import java.util.List;

public class TaskValidatorImpl implements TaskValidator {

    @Override
    public void validate(Task task) throws TaskValidationException {
        if (Boolean.TRUE.equals(task.getFinished()) && !allSubTasksFinished(task.getSubTasks())) {
            throw new NotAllSubTasksFinishedException("All sub-tasks should be finished before marking parent task as finished");
        }

        if (moreThanOneSubTasksLevel(task.getSubTasks())) {
            throw new NestedSubTasksException("More than one sub-tasks level is not allowed");
        }
    }

    //Simplifying sub-tasks nesting problem by allowing only one sub-task level. No need to solve circular dependency
    private static boolean moreThanOneSubTasksLevel(List<Task> subTasks) {
        return !subTasks.stream().allMatch(t -> t.getSubTasks().isEmpty());
    }

    private static boolean allSubTasksFinished(List<Task> subTasks) {
        return subTasks.stream().allMatch(t -> Boolean.TRUE.equals(t.getFinished()));
    }
}
