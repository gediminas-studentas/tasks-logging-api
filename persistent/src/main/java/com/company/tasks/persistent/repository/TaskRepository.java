package com.company.tasks.persistent.repository;

import com.company.tasks.persistent.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
