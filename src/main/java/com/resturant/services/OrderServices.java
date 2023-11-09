package com.resturant.services;

import com.resturant.error.DublicateException;
import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.model.Order;
import com.resturant.repo.OrdersRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServices {

	@Autowired
	private OrdersRepo  ordersRepo;
	
	public OrderDto createOrder(Order order) {
		Optional<Order> order1= ordersRepo.findById(order.getId());
		if(order1.isPresent())
			throw new DublicateException("It's already exited");

		order.setCreated_at(LocalDateTime.now());
		order.setUpdate_at(LocalDateTime.now());
		return OrderDto.maptoOrderDto(ordersRepo.save(order));
	}
	
	public OrderDto updateOrder(Order entity) {
		Order order = ordersRepo.findById(entity.getId()).orElseThrow(() -> new ErrorNotFoundException("Not found Order"));
		order.setCreated_at(entity.getCreated_at());
		order.setUpdate_at(LocalDateTime.now());
		return OrderDto.maptoOrderDto(order);

	}

	public List<OrderDto> findAllOrders (){
		List<Order> orders= ordersRepo.findAll();
		return OrderDto.maptoOrderDtos(orders);
	}

	public Order findById(Long id){
		return ordersRepo.findById(id).orElseThrow(()-> new ErrorNotFoundException("Not found order"));
	}

	public void delete(Long id){
		ordersRepo.deleteById(id);
	}
	
	
	
}
