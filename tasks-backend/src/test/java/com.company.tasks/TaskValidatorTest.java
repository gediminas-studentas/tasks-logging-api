package com.company.tasks;

import com.company.tasks.exception.validator.NestedSubTasksException;
import com.company.tasks.exception.validator.NotAllSubTasksFinishedException;
import com.company.tasks.persistent.entity.Task;
import com.company.tasks.validator.TaskValidatorImpl;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class TaskValidatorTest {

    @Test(expected = NotAllSubTasksFinishedException.class)
    public void When_FinishingParentTaskButNotAllSubTasksFinished_Expect_Exception() {
        new TaskValidatorImpl().validate(Task.builder()
                .withFinished(true)
                .withSubTasks(ImmutableList.of(Task.builder().withFinished(false).build()))
                .build()
        );
    }

    @Test(expected = Test.None.class)
    public void When_NotFinishingParentTaskAndNotAllSubTasksFinished_Expect_NoException() {
        new TaskValidatorImpl().validate(Task.builder()
                .withFinished(false)
                .withSubTasks(ImmutableList.of(Task.builder().withFinished(false).build()))
                .build()
        );
    }

    @Test(expected = Test.None.class)
    public void When_FinishingParentTaskAndAllSubTasksFinished_Expect_NoException() {
        new TaskValidatorImpl().validate(Task.builder()
                .withFinished(true)
                .withSubTasks(ImmutableList.of(Task.builder().withFinished(true).build(), Task.builder().withFinished(true).build()))
                .build()
        );
    }

    @Test(expected = NestedSubTasksException.class)
    public void When_ThereAreMoreThanOneSubTasksLevel_Expect_Exception() {
        new TaskValidatorImpl().validate(Task.builder()
                .withSubTasks(ImmutableList.of(Task.builder().withSubTasks(
                        ImmutableList.of(Task.builder().build())
                ).build()))
                .build()
        );
    }
}
