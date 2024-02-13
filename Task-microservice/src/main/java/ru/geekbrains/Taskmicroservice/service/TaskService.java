package ru.geekbrains.Taskmicroservice.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.Taskmicroservice.dto.TaskDTO;
import ru.geekbrains.Taskmicroservice.entity.Executor;
import ru.geekbrains.Taskmicroservice.entity.Task;
import ru.geekbrains.Taskmicroservice.repository.ExecutorRepository;
import ru.geekbrains.Taskmicroservice.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ExecutorRepository executorRepository;

    public TaskService(TaskRepository taskRepository, ExecutorRepository executorRepository) {
        this.taskRepository = taskRepository;
        this.executorRepository = executorRepository;
    }

    public Task add(TaskDTO dto) {
        return taskRepository.save(new Task(dto.getDescription()));
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getByStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> changeStatus(long id, Task.Status newStatus) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            task.get().setStatus(newStatus);
            taskRepository.save(task.get());
        }
        return task;
    }

    public Optional<Task> deleteById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        return task;
    }

    public Optional<Task> assignTask(long idTask, long idExecutor) {
        Optional<Task> task = taskRepository.findById(idTask);
        Optional<Executor> executor = executorRepository.findById(idExecutor);
        if (task.isPresent() && executor.isPresent()) {
            task.get().getExecutors().add(executor.get());
            taskRepository.save(task.get());
            executor.get().getTasks().add(task.get());
            executorRepository.save(executor.get());
        } else {
            task = Optional.empty();
        }
        return task;
    }
}