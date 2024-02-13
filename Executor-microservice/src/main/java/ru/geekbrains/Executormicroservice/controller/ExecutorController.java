package ru.geekbrains.Executormicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Executormicroservice.dto.ExecutorDTO;
import ru.geekbrains.Executormicroservice.entity.Executor;
import ru.geekbrains.Executormicroservice.service.ExecutorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/executors")
public class ExecutorController {
    private final ExecutorService executorService;

    public ExecutorController(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Executor>> allExecutors() {
        return new ResponseEntity<>(executorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Executor> findById(@PathVariable long id) {
        Optional<Executor> executor = executorService.findById(id);
        return executor.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Executor> add(@RequestBody ExecutorDTO executorDTO) {
        return new ResponseEntity<>(executorService.add(executorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable long id) {
        if (executorService.deleteById(id).isPresent()) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}