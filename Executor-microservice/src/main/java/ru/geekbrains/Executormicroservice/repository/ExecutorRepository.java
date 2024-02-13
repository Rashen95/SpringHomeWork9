package ru.geekbrains.Executormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Executormicroservice.entity.Executor;

@Repository
public interface ExecutorRepository extends JpaRepository<Executor, Long> {
}