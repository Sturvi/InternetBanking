package com.example.internetbanking.model.mapper;

import com.example.internetbanking.model.User;
import com.example.internetbanking.model.dto.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    User toUser (UserDTO dto);

    @Mapping (target = "id", source = "id")
    UserDTO toUserDTO (User user);

}