package com.resturant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.model.Order;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Order, Long>{

       List<Order> findByUserId(Long id);
       List<Order> findByUserIdAndRestaurantId(Long userId,Long restaurantId);


}
