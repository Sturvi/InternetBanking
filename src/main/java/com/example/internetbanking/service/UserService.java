package com.example.internetbanking.service;

import com.example.internetbanking.exceptions.UserNotFoundException;
import com.example.internetbanking.model.User;
import com.example.internetbanking.model.dto.UserDTO;
import com.example.internetbanking.model.mapper.UserMapper;
import com.example.internetbanking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save (User user) {
        userRepository.save(user);
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    public User findUserById(Long id) {
        validateId(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public BigDecimal getBalance(Long id) {
        User user = findUserById(id);
        return user.getBalance();
    }

    public UserDTO getUserDtoById(Long id) {
        User user = findUserById(id);
        return userMapper.toUserDTO(user);
    }

}
