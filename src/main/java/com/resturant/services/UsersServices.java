package com.resturant.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.resturant.error.DublicateException;
import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.dto.OrderDto;
import com.resturant.repo.OrdersRepo;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.model.User;
import com.resturant.repo.UsersRepo;

@Service
public class UsersServices {

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private OrdersRepo ordersRepo;

	public User createUser(User user) {
		if(!user.getEmail().isEmpty() && user.getEmail()!=null) {
			Optional<User> saveUser = usersRepo.findByEmail(user.getEmail());
			if (saveUser.isPresent())
				throw new DublicateException("This is email already used with another author");

			user.setCreated_at(LocalDate.now());
			user.setUpdated_at(LocalDate.now());
			return usersRepo.save(user);
		}
		else
			throw new ErrorNotFoundException("Not found email");
	}
	
	
	public User updateUser(User entity) {

		User user =usersRepo.findById(entity.getId()).orElseThrow(
				()-> new ErrorNotFoundException("User Not found")
		);
		entity.setCreated_at(user.getCreated_at());
		entity.setUpdated_at(LocalDate.now());
		return usersRepo.save(entity);

	}
	
	public Optional<User> findById(Long id) {

		Optional<User> user=usersRepo.findById(id);
		if (user.isEmpty())
			throw new ErrorNotFoundException("Not found user");

		return user;
	}

	public Optional<User> findByEmail(String eamil){

		Optional<User> user=usersRepo.findByEmail(eamil);
		if (user.isEmpty())
			throw new ErrorNotFoundException("Not found user");

		return user;
	}
	public List<User> findAll(){
		return usersRepo.findAll();
	}

	public List<OrderDto> findAllOrders(Long id){


		return ordersRepo.findByUserId(id).stream()
				.map(OrderDto::maptoOrderDto)
				.collect(Collectors.toList());
	}


	public List<OrderDto> findAllOrders(Long userId, Long restaurantId){
		return ordersRepo.findByUserIdAndRestaurantId( userId,restaurantId).stream()
				.map(OrderDto::maptoOrderDto)
				.collect(Collectors.toList());
	}
	
}
