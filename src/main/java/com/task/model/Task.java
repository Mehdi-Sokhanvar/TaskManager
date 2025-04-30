package com.task.model;

import com.task.model.enums.PRIORITY;
import com.task.model.enums.STATUS;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.UUID;



@Entity
public class Task extends BaseEntity<UUID> {


    private String title;

    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PRIORITY priority;

    @Enumerated(EnumType.STRING)
    private STATUS status;

    @ManyToOne
    private User user;


    public Task(UUID uuid, String title, String description, LocalDate dueDate, PRIORITY priority, STATUS status, User user) {
        super(uuid);
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.user = user;
    }

    public Task() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class Builder {
        private UUID uuid;
        private String title;
        private String description;
        private LocalDate dueDate;
        private PRIORITY priority;
        private STATUS status;
        private User user;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder priority(PRIORITY priority) {
            this.priority = priority;
            return this;
        }

        public Builder status(STATUS status) {
            this.status = status;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Task build() {
            return new Task(uuid, title, description, dueDate, priority, status, user);
        }
    }
}
