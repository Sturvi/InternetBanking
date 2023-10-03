package com.example.internetbanking.service;

import com.example.internetbanking.exceptions.InsufficientFundsException;
import com.example.internetbanking.exceptions.UserNotFoundException;
import com.example.internetbanking.model.User;
import com.example.internetbanking.model.dto.UserDTO;
import com.example.internetbanking.model.mapper.UserMapper;
import com.example.internetbanking.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUserById(Long id) {
        validateId(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public BigDecimal getBalance(Long id) {
        User user = findUserById(id);
        return user.getBalance();
    }

    public UserDTO getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toUserDTO(user);
    }

    @Transactional
    public UserDTO putMoney(Long id, BigDecimal amount) {
        validateId(id);
        validateAmount(amount);
        User user = findUserById(id);
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    @Transactional
    public UserDTO takeMoney(Long id, BigDecimal amount) {
        validateId(id);
        validateAmount(amount);
        User user = findUserById(id);
        BigDecimal newBalance = user.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        user.setBalance(newBalance);
        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }
}
