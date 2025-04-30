package com.task.model;

import jakarta.persistence.*;


@Entity
public class Role extends BaseEntity<Long> {

    private String name;

    public Role(Long aLong) {
        super(aLong);
    }

    public Role() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
