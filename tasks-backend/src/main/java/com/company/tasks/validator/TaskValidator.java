package com.company.tasks.validator;

import com.company.tasks.exception.validator.TaskValidationException;
import com.company.tasks.persistent.entity.Task;

public interface TaskValidator {

    void validate(Task task) throws TaskValidationException;

}
