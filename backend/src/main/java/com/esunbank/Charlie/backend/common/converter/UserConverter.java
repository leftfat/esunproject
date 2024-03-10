package com.esunbank.Charlie.backend.common.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esunbank.Charlie.backend.common.domain.dto.LoginDto;
import com.esunbank.Charlie.backend.common.domain.dto.UserDto;
import com.esunbank.Charlie.backend.common.domain.entity.User;
import com.esunbank.Charlie.backend.common.domain.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserConverter {
    @Mapping(target = "registrationTime", ignore = true)
    UserDto toUserDto(UserRequest userRequest);
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "userId", ignore = true)
    User toUser(UserDto userDto);
    LoginDto toLoginDto(User user);
}