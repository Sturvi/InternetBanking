package com.example.internetbanking.service;


import com.example.internetbanking.exceptions.InsufficientFundsException;
import com.example.internetbanking.model.Operation;
import com.example.internetbanking.model.OperationType;
import com.example.internetbanking.model.User;
import com.example.internetbanking.model.dto.UserDTO;
import com.example.internetbanking.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserService userService;
    private final OperationService operationService;
    private final UserMapper userMapper;

    @Transactional
    public UserDTO putMoney(Long id, BigDecimal amount) {
        validateAmount(amount);
        User user = userService.findUserById(id);
        user.setBalance(user.getBalance().add(amount));
        userService.save(user);

        var operation = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .user(user)
                .amount(amount)
                .build();
        operationService.save(operation);

        return userMapper.toUserDTO(user);
    }

    @Transactional
    public UserDTO takeMoney(Long id, BigDecimal amount) {
        validateAmount(amount);
        User user = userService.findUserById(id);
        BigDecimal newBalance = user.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        user.setBalance(newBalance);
        userService.save(user);

        var operation = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .user(user)
                .amount(amount)
                .build();
        operationService.save(operation);

        return userMapper.toUserDTO(user);
    }


    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public List<Operation> getOperationList(Long userId, LocalDate startDate, LocalDate endDate) {
        var user = userService.findUserById(userId);

        var startDateTime = Optional.ofNullable(startDate)
                .map(date -> LocalDateTime.of(date, LocalTime.MIDNIGHT))
                .orElse(null);
        var endDateTime = Optional.ofNullable(endDate)
                .map(date -> LocalDateTime.of(date, LocalTime.MIDNIGHT))
                .orElse(null);

        if (startDateTime != null && endDateTime != null) {
            return operationService.getOperationsBetweenDates(user, startDateTime, endDateTime);
        }

        if (startDateTime != null) {
            return operationService.getOperationsAfterDate(user, startDateTime);
        }

        if (endDateTime != null) {
            return operationService.getOperationsBeforeDate(user, endDateTime);
        }

        return operationService.getAllOperations(user);
    }
}
