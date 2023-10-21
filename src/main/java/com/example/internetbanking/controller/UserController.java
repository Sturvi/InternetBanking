package com.example.internetbanking.controller;

import com.example.internetbanking.exceptions.UserNotFoundException;
import com.example.internetbanking.model.Operation;
import com.example.internetbanking.model.dto.UserDTO;
import com.example.internetbanking.service.TransactionService;
import com.example.internetbanking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserDtoById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        BigDecimal balance = userService.getBalance(id);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/{id}/put")
    public ResponseEntity<UserDTO> putMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        UserDTO userDTO = transactionService.putMoney(id, amount);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}/take")
    public ResponseEntity<UserDTO> takeMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        UserDTO userDTO = transactionService.takeMoney(id, amount);
        return ResponseEntity.ok(userDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }


    @GetMapping("/{id}/operations")
    public ResponseEntity<List<Operation>> getOperationList(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        List<Operation> operations = transactionService.getOperationList(id, startDate, endDate);
        return ResponseEntity.ok(operations);
    }
}
