package com.company.tasks.exception.validator;

public class NestedSubTasksException extends TaskValidationException {
    public NestedSubTasksException() {
    }

    public NestedSubTasksException(String message) {
        super(message);
    }

    public NestedSubTasksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NestedSubTasksException(Throwable cause) {
        super(cause);
    }

    public NestedSubTasksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
