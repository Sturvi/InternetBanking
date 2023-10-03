package com.example.internetbanking.model.dto;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserDTO {

    @Id
    private Long id;

    private BigDecimal balance;

}
