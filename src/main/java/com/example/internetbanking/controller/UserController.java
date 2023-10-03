package com.example.internetbanking.controller;

import com.example.internetbanking.exceptions.UserNotFoundException;
import com.example.internetbanking.model.dto.UserDTO;
import com.example.internetbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        BigDecimal balance = userService.getBalance(id);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/{id}/put")
    public ResponseEntity<UserDTO> putMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        UserDTO userDTO = userService.putMoney(id, amount);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}/take")
    public ResponseEntity<UserDTO> takeMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        UserDTO userDTO = userService.takeMoney(id, amount);
        return ResponseEntity.ok(userDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
