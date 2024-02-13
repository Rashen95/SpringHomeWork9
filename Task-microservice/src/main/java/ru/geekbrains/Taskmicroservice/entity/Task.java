package ru.geekbrains.Taskmicroservice.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String description;
    @NonNull
    private Status status;
    @NonNull
    private LocalDateTime dateOfCreation;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "task_executor",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "executor_id"))
    private Set<Executor> executors = new HashSet<>();

    public Task() {
    }

    public Task(@NonNull String description) {
        this.description = description;
        this.status = Status.NOT_STARTED;
        this.dateOfCreation = LocalDateTime.now();
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    @NonNull
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Set<Executor> getExecutors() {
        return executors;
    }

    public void setExecutors(Set<Executor> executors) {
        this.executors = executors;
    }

    public long getId() {
        return id;
    }

    public enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        DONE
    }
}