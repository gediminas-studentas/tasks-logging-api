package com.company.tasks.persistent.repository;

import com.company.tasks.persistent.entity.TaskMapping;
import com.company.tasks.persistent.entity.TaskMappingId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskMappingRepository extends CrudRepository<TaskMapping, TaskMappingId> {
    List<TaskMapping> findByParentIdOrChildId(int parentId, int childId);
}
