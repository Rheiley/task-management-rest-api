package com.rheiley.taskmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String description;
    private boolean completed;
}
