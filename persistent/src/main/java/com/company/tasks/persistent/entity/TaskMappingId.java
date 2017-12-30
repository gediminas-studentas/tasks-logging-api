package com.company.tasks.persistent.entity;

import java.io.Serializable;

public class TaskMappingId implements Serializable {

    private Integer parentId;
    private Integer childId;

    private TaskMappingId() {
    }

    public TaskMappingId(Integer parentId, Integer childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getChildId() {
        return childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskMappingId that = (TaskMappingId) o;

        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        return childId != null ? childId.equals(that.childId) : that.childId == null;
    }

    @Override
    public int hashCode() {
        int result = parentId != null ? parentId.hashCode() : 0;
        result = 31 * result + (childId != null ? childId.hashCode() : 0);
        return result;
    }
}
