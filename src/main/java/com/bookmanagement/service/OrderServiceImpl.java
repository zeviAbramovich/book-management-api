package com.bookmanagement.service;

import com.bookmanagement.dto.OrderCreateDTO;
import com.bookmanagement.dto.OrderResponseDTO;
import com.bookmanagement.enums.OrderStatus;
import com.bookmanagement.mapper.OrderMapper;
import com.bookmanagement.model.Book;
import com.bookmanagement.model.Order;
import com.bookmanagement.model.User;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.OrderRepository;
import com.bookmanagement.repository.UserRepository;
import com.bookmanagement.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderMapper.toResponseDtoList(orderRepository.findAll());
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = findOrderByIdOrThrow(id);
        return orderMapper.toResponseDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderCreateDTO orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + orderDto.getUserId() + " not found"));
        System.out.println("User found");
        List<Book> books = bookRepository.findAllById(orderDto.getBookIds());
        System.out.println("books found");
        if (books.size() != orderDto.getBookIds().size()) {
            throw new EntityNotFoundException("One or more books not found");
        }

        Order order = Order.builder()
                .user(user)
                .books(books)
                .totalAmount(orderDto.getTotalAmount())
                .status(OrderStatus.PENDING)
                .build();
        System.out.println("order is created and now call to save method");
        Order saved = orderRepository.save(order);
        System.out.println("order is saved, calling orderMapper.toResponseDto(saved)");
        return orderMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderCreateDTO orderDto) {
        Order existing = findOrderByIdOrThrow(id);

        existing.setTotalAmount(orderDto.getTotalAmount());

        if (orderDto.getBookIds() != null && !orderDto.getBookIds().isEmpty()) {
            List<Book> books = bookRepository.findAllById(orderDto.getBookIds());
            existing.setBooks(books);
        }

        Order updated = orderRepository.save(existing);
        return orderMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order with ID " + id + " not found");
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrderStatus(Long id, String status) {
        Order order = findOrderByIdOrThrow(id);
        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(orderStatus);
            Order updated = orderRepository.save(order);
            return orderMapper.toResponseDto(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }

    @Override
    public boolean isOrderOwner(Long orderId, String username) {
        return orderRepository.findById(orderId)
                .map(order -> order.getUser().getUsername().equals(username))
                .orElse(false);
    }

    private Order findOrderByIdOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found"));
    }
}
