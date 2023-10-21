package com.example.internetbanking.repository;

import com.example.internetbanking.model.Operation;
import com.example.internetbanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByUserAndCreatedAtBetween(User user, LocalDateTime startDate, LocalDateTime endDate);
    List<Operation> findByUserAndCreatedAtBefore(User user, LocalDateTime date);
    List<Operation> findByUserAndCreatedAtAfter(User user, LocalDateTime date);
    List<Operation> findByUser(User user);
}
