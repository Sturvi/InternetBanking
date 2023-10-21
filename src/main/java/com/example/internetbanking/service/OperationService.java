package com.example.internetbanking.service;

import com.example.internetbanking.model.Operation;
import com.example.internetbanking.model.User;
import com.example.internetbanking.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;

    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    public List<Operation> getOperationsBetweenDates(User user, LocalDateTime startDate, LocalDateTime endDate) {
        return operationRepository.findByUserAndCreatedAtBetween(user, startDate, endDate);
    }

    public List<Operation> getOperationsBeforeDate(User user, LocalDateTime date) {
        return operationRepository.findByUserAndCreatedAtBefore(user, date);
    }

    public List<Operation> getOperationsAfterDate(User user, LocalDateTime date) {
        return operationRepository.findByUserAndCreatedAtAfter(user, date);
    }

    public List<Operation> getAllOperations (User user) {
        return operationRepository.findByUser(user);
    }
}
