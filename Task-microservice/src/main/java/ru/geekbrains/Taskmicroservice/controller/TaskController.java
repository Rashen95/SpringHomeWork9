package ru.geekbrains.Taskmicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Taskmicroservice.dto.TaskDTO;
import ru.geekbrains.Taskmicroservice.entity.Task;
import ru.geekbrains.Taskmicroservice.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> allTasks() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/bystatus")
    public ResponseEntity<List<Task>> tasksByStatus(@RequestParam("status") Task.Status status) {
        return new ResponseEntity<>(taskService.getByStatus(status), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO dto) {
        return new ResponseEntity<>(taskService.add(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id_task}/{id_executor}")
    public ResponseEntity<Task> assignTask(@PathVariable("id_task") long idTask, @PathVariable("id_executor") long idExecutor) {
        Optional<Task> task = taskService.assignTask(idTask, idExecutor);
        return task.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public HttpStatus changeTaskStatus(@PathVariable long id, @RequestParam("status") Task.Status newStatus) {
        if (taskService.changeStatus(id, newStatus).isPresent()) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTask(@PathVariable long id) {
        if (taskService.deleteById(id).isPresent()) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}