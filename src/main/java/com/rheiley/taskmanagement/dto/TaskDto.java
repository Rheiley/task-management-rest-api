package com.rheiley.taskmanagement.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto{
    private Long id;
    private String taskName;
    private String description;
    private boolean completed;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        TaskDto other = (TaskDto) obj;
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.taskName, other.taskName) &&
                Objects.equals(this.description, other.description) &&
                this.completed == other.completed;
    }
}
