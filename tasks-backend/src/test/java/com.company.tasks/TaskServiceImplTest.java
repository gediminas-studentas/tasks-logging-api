package com.company.tasks;

import com.company.tasks.entity.TaskDTO;
import com.company.tasks.persistent.entity.Task;
import com.company.tasks.persistent.entity.TaskMapping;
import com.company.tasks.persistent.repository.TaskMappingRepository;
import com.company.tasks.persistent.repository.TaskRepository;
import com.company.tasks.validator.TaskValidator;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Test
    public void When_CreatingTask_Expect_TaskValidation() {
        //Given:
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskValidator taskValidator = mock(TaskValidator.class);
        TaskServiceImpl taskService = new TaskServiceImpl(
                taskRepository,
                mock(TaskMappingRepository.class),
                taskValidator
        );
        when(taskRepository.save((Task) any())).thenReturn(Task.builder().build());

        //When:
        taskService.createTask(TaskDTO.builder().build());

        //Then:
        verify(taskValidator, times(1)).validate(any());
    }

    @Test
    public void When_UpdatingTask_Expect_TaskValidation() {
        //Given:
        TaskValidator taskValidator = mock(TaskValidator.class);
        TaskServiceImpl taskService = new TaskServiceImpl(
                mock(TaskRepository.class),
                mock(TaskMappingRepository.class),
                taskValidator
        );

        //When:
        taskService.updateTask(TaskDTO.builder().build());

        //Then:
        verify(taskValidator, times(1)).validate(any());
    }

    @Test
    public void When_DeletingTask_Expect_MappingsDelete() {
        //Given:
        TaskMappingRepository taskMappingRepository = mock(TaskMappingRepository.class);
        TaskServiceImpl taskService = new TaskServiceImpl(
                mock(TaskRepository.class),
                taskMappingRepository,
                mock(TaskValidator.class)
        );
        List<TaskMapping> cascadingMappings = ImmutableList.of(new TaskMapping(1, 100), new TaskMapping(200, 1));
        when(taskMappingRepository.findByParentIdOrChildId(1, 1)).thenReturn(cascadingMappings);

        //When:
        taskService.deleteTask(1);

        //Then:
        verify(taskMappingRepository, times(1)).delete(cascadingMappings.get(0));
        verify(taskMappingRepository, times(1)).delete(cascadingMappings.get(1));
    }
}
