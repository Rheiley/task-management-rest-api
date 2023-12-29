package com.rheiley.taskmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    private Long id;

    @Column(unique = true)
    private String uid;

    public void setId(Long id) {
        this.id = id;
    }

}
