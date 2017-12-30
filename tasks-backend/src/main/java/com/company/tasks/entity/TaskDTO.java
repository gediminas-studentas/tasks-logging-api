package com.company.tasks.entity;

import java.util.Collections;
import java.util.List;

public class TaskDTO {

    private Integer id;

    private String name;
    private Long durationSpentInSeconds;
    private String groupName;
    private String assignee;
    private List<Integer> subTaskIds;
    private Boolean finished;

    private final static Long DEFAULT_DURATION = 0L;
    private final static Boolean DEFAULT_FINISHED = false;

    private TaskDTO() {
    }

    public static Builder builder() {
        return new Builder();
    }

    private TaskDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.durationSpentInSeconds = builder.durationSpentInSeconds != null ? builder.durationSpentInSeconds : DEFAULT_DURATION;
        this.groupName = builder.groupName;
        this.assignee = builder.assignee;
        this.subTaskIds = builder.subTaskIds;
        this.finished = builder.finished != null ? builder.finished : DEFAULT_FINISHED;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAssignee() {
        return assignee;
    }

    public Long getDurationSpentInSeconds() {
        return durationSpentInSeconds;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public Boolean getFinished() {
        return finished;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Long durationSpentInSeconds;
        private String groupName;
        private String assignee;
        private List<Integer> subTaskIds = Collections.emptyList();
        private Boolean finished;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDurationSpentInSeconds(Long durationSpentInSeconds) {
            this.durationSpentInSeconds = durationSpentInSeconds;
            return this;
        }

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder withAssignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder withSubTaskIds(List<Integer> subTaskIds) {
            this.subTaskIds = subTaskIds;
            return this;
        }

        public Builder withFinished(Boolean finished) {
            this.finished = finished;
            return this;
        }

        public TaskDTO build() {
            return new TaskDTO(this);
        }
    }
    
}
