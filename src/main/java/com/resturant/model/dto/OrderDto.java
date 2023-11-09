package com.resturant.model.dto;

import com.resturant.model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {


    private Long id;
    private String status;
    private String address;
    private LocalDateTime created_at;
    private String restaurantName;
    private String userName;
    private List<ItemDto> items;

    public static OrderDto maptoOrderDto(Order order){
        OrderDto orderDto= new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setAddress(order.getAddress());
        orderDto.setCreated_at(order.getCreated_at());
        orderDto.setRestaurantName(order.getRestaurant().getName());
        orderDto.setUserName(order.getUser().getUserName());
        orderDto.setItems(
                order.getItems().stream()
                        .map(ItemDto::mapToItemDto)
                        .collect(Collectors.toList())
        );
        return orderDto;
    }

    public static List<OrderDto> maptoOrderDtos(List<Order> orders){
        List<OrderDto>orderDtos= new ArrayList<>();
        orders.forEach(order->{
            OrderDto orderDto= new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            orderDto.setAddress(order.getAddress());
            orderDto.setCreated_at(order.getCreated_at());
            orderDto.setRestaurantName(order.getRestaurant().getName());
            orderDto.setUserName(order.getUser().getUserName());
            orderDto.setItems(
                    order.getItems().stream()
                            .map(ItemDto::mapToItemDto)
                            .collect(Collectors.toList())
            );
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }




}
