package com.company.tasks.persistent.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    @Column(name = "duration_spent_in_seconds")
    private Long durationSpentInSeconds;
    @Column(name = "group_name")
    private String groupName;
    private String assignee;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable
            (
                    name="task_mapping",
                    joinColumns={ @JoinColumn(name="parent_id", referencedColumnName="id") },
                    inverseJoinColumns={ @JoinColumn(name="child_id", referencedColumnName="id")}
            )
    private List<Task> subTasks;
    private Boolean finished;

    private Task() {
    }

    public static Builder builder() {
        return new Builder();
    }

    private Task(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.durationSpentInSeconds = builder.durationSpentInSeconds;
        this.groupName = builder.groupName;
        this.assignee = builder.assignee;
        this.subTasks = builder.subTasks;
        this.finished = builder.finished;
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

    public List<Task> getSubTasks() {
        return subTasks;
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
        private List<Task> subTasks = Collections.emptyList();
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

        public Builder withSubTasks(List<Task> subTasks) {
            this.subTasks = subTasks;
            return this;
        }

        public Builder withFinished(Boolean finished) {
            this.finished = finished;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
    
}
