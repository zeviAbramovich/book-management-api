package com.bookmanagement.mapper;

import com.bookmanagement.dto.OrderCreateDTO;
import com.bookmanagement.dto.OrderResponseDTO;
import com.bookmanagement.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BookMapper.class})
public interface OrderMapper {
    @Mapping(target = "user", source = "user")  // Uses UserMapper.toSummaryDto
    @Mapping(target = "books", source = "books")  // Uses BookMapper.toSummaryDtoList
    OrderResponseDTO toResponseDto(Order order);

    List<OrderResponseDTO> toResponseDtoList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateOrderFromDto(OrderCreateDTO dto, @MappingTarget Order order);
}
