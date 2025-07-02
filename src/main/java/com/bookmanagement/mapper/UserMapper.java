package com.bookmanagement.mapper;

import com.bookmanagement.dto.UserCreateDTO;
import com.bookmanagement.dto.UserResponseDTO;
import com.bookmanagement.dto.UserSummaryDTO;
import com.bookmanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateDTO dto);

    @Mapping(target = "orderCount", expression = "java(user.getOrders() != null ? user.getOrders().size() : 0)")
    UserResponseDTO toResponseDto(User user);

    UserSummaryDTO toSummaryDto(User user);

    List<UserResponseDTO> toResponseDtoList(List<User> users);
    List<UserSummaryDTO> toSummaryDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateUserFromDto(UserCreateDTO dto, @MappingTarget User user);
}
