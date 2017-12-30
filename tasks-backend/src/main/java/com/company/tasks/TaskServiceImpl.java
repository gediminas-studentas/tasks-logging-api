package com.company.tasks;

import com.company.tasks.entity.TaskDTO;
import com.company.tasks.entity.factory.TaskDTOFactory;
import com.company.tasks.entity.factory.TaskFactory;
import com.company.tasks.exception.SubTaskNotFoundException;
import com.company.tasks.exception.validator.TaskValidationException;
import com.company.tasks.persistent.entity.Task;
import com.company.tasks.persistent.repository.TaskMappingRepository;
import com.company.tasks.persistent.repository.TaskRepository;
import com.company.tasks.validator.TaskValidator;

import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMappingRepository taskMappingRepository;
    private final TaskFactory taskFactory;
    private final TaskValidator taskValidator;

    public TaskServiceImpl(
            TaskRepository taskRepository,
            TaskMappingRepository taskMappingRepository,
            TaskValidator taskValidator
    ) {
        this.taskRepository = taskRepository;
        this.taskFactory = new TaskFactory(id -> Optional.ofNullable(taskRepository.findOne(id)));
        this.taskMappingRepository = taskMappingRepository;
        this.taskValidator = taskValidator;
    }

    @Override
    public TaskDTO getTask(int taskId) {
        Task task = taskRepository.findOne(taskId);
        if (task == null) {
            return null;
        }

        return TaskDTOFactory.buildFromTask(task);
    }

    @Override
    public void updateTask(TaskDTO task) throws TaskValidationException, SubTaskNotFoundException {
        Task updatedTask = taskFactory.buildFromTaskDTO(task);
        taskValidator.validate(updatedTask);
        taskRepository.save(updatedTask);
    }

    @Override
    public Integer createTask(TaskDTO task) throws TaskValidationException, SubTaskNotFoundException {
        Task newTask = taskFactory.buildFromTaskDTO(task);
        taskValidator.validate(newTask);
        return taskRepository.save(newTask).getId();
    }

    @Override
    public void deleteTask(int taskId) {
        taskMappingRepository.findByParentIdOrChildId(taskId, taskId).forEach(taskMappingRepository::delete);
        taskRepository.delete(taskId);
    }
}
