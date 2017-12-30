package com.company.tasks.exception.validator;

public class NotAllSubTasksFinishedException extends TaskValidationException {
    public NotAllSubTasksFinishedException() {
    }

    public NotAllSubTasksFinishedException(String message) {
        super(message);
    }

    public NotAllSubTasksFinishedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllSubTasksFinishedException(Throwable cause) {
        super(cause);
    }

    public NotAllSubTasksFinishedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
