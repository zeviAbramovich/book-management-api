package com.bookmanagement.service;

import com.bookmanagement.dto.*;
import com.bookmanagement.mapper.UserMapper;
import com.bookmanagement.mapper.OrderMapper;
import com.bookmanagement.model.User;
import com.bookmanagement.repository.UserRepository;
import com.bookmanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDtoList(userRepository.findAll());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = findUserByIdOrThrow(id);
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User saved = userRepository.save(user);
        return userMapper.toResponseDto(saved);
    }

    @Override
    public List<OrderResponseDTO> getUserOrders(Long userId) {
        User user = findUserByIdOrThrow(userId);
        return orderMapper.toResponseDtoList(user.getOrders());
    }

    @Override
    public List<UserResponseDTO> getUsersWithMoreThanOrders(int orderCount) {
        List<User> users = userRepository.findUsersWithMoreThanOrders(orderCount);
        return userMapper.toResponseDtoList(users);
    }

    @Override
    public List<UserOrderStatsDTO> getUserOrderStatistics() {
        return userRepository.findUserOrderStatistics();
    }

    @Override
    public List<UserResponseDTO> getUsersWithoutOrders() {
        List<User> users = userRepository.findUsersWithoutOrders();
        return userMapper.toResponseDtoList(users);
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }
}
