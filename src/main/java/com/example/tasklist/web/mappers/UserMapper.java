package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.user.User;
import com.example.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
