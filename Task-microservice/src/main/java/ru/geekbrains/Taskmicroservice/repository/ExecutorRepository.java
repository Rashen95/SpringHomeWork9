package ru.geekbrains.Taskmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Taskmicroservice.entity.Executor;

@Repository
public interface ExecutorRepository extends JpaRepository<Executor, Long> {
}