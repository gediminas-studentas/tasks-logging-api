package com.company.tasks.entity.factory;

import com.company.tasks.persistent.entity.Task;

import java.util.Optional;

@FunctionalInterface
public interface TaskByIdSupplier {

    /**
     * Gets a task by provided id.
     *
     * @return a task
     */
    Optional<Task> get(Integer id);
}