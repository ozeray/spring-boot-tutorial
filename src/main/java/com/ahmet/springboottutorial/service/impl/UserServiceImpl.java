package com.ahmet.springboottutorial.service.impl;

import com.ahmet.springboottutorial.dto.UserDto;
import com.ahmet.springboottutorial.entity.User;
import com.ahmet.springboottutorial.exception.EmailAlreadyExistsException;
import com.ahmet.springboottutorial.exception.ResourceNotFoundException;
import com.ahmet.springboottutorial.mapper.AutoUserMapper;
import com.ahmet.springboottutorial.repository.UserRepository;
import com.ahmet.springboottutorial.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException();
        }

//        User user = modelMapper.map(userDto, User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @SneakyThrows
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                    .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
