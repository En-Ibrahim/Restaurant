package com.resturant.controller;

import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.Restaurant;
import com.resturant.services.ResturantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/restaurant")
public class ResturantController {

    @Autowired
    private ResturantServices resturantServices;

    @PostMapping
    public ResponseEntity<?> createResturant(@RequestBody Restaurant restaurant){
            return  ResponseEntity.ok(resturantServices.createResturant(restaurant));
    }

    @PutMapping
    public ResponseEntity<?> updateResturant(@RequestBody Restaurant restaurant){
            return  ResponseEntity.ok(resturantServices.updateResturant(restaurant));
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(resturantServices.findAll());
    }

    @GetMapping("/by-name-restaurant/{id}")
    public ResponseEntity<?> findAllMenusByResturantId(@PathVariable("id") Long id){
        try {
            return  ResponseEntity.ok(resturantServices.findAllMenusByResturantId(id));
        }catch (Exception e){
            throw new ErrorNotFoundException("Invalid input ID");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

            return  ResponseEntity.ok(resturantServices.findById(id));
    }


    @DeleteMapping
    public ResponseEntity<?> delete(Long id){
        try {
            resturantServices.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }catch (Exception e){
            throw new ErrorNotFoundException("It's already deleted");
        }
    }






}
