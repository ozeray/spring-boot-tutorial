package com.ahmet.springboottutorial.mapper;

import com.ahmet.springboottutorial.dto.UserDto;
import com.ahmet.springboottutorial.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
