package com.resturant.controller;

import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.Order;
import com.resturant.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderServices orderServices;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order){

            return ResponseEntity.ok(orderServices.createOrder(order));
    }
    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
            return ResponseEntity.ok(orderServices.updateOrder(order));
    }


    @GetMapping
    public ResponseEntity<?> findAllOrders(){
        return ResponseEntity.ok(orderServices.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderServices.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            orderServices.delete(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch (Exception e){
              throw new ErrorNotFoundException("It's already deleted");
        }
    }



}
