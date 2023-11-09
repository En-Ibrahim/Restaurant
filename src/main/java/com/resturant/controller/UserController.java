package com.resturant.controller;

import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.User;
import com.resturant.model.dto.OrderDto;
import com.resturant.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersServices usersServices;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
            return ResponseEntity.ok(usersServices.createUser(user));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
          return ResponseEntity.ok(usersServices.updateUser(user));
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(usersServices.findAll());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id){
//            return ResponseEntity.ok(usersServices.findById(id));
//    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(usersServices.findByEmail(email));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<?> findUserOrders(@PathVariable("id") Long id){
        try {
            List<OrderDto> orderDtos= usersServices.findAllOrders(id);
            return ResponseEntity.ok(orderDtos);
        }catch (Exception e){
           throw new ErrorNotFoundException("Invalid input ID");
        }
    }

    @GetMapping("/{id}/orders/{restaurantId}")
    public ResponseEntity<?> findUserOrdersByRestaurant(@PathVariable("id") Long userId,@PathVariable("restaurantId") Long restaurantId){
        try {
            List<OrderDto> orderDtos= usersServices.findAllOrders(userId,restaurantId);
            return ResponseEntity.ok(orderDtos);
        }catch (Exception e){
            throw new ErrorNotFoundException("Invalid input ID");
        }
    }





}


