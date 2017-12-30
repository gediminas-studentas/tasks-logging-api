package com.company.tasks.persistent.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_mapping")
@IdClass(TaskMappingId.class)
public class TaskMapping {

    @Id
    @Column(name = "parent_id")
    private Integer parentId;
    @Id
    @Column(name = "child_id")
    private Integer childId;

    private TaskMapping() {
    }

    public TaskMapping(Integer parentId, Integer childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getChildId() {
        return childId;
    }
}
