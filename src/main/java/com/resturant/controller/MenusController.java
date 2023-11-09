package com.resturant.controller;

import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.Item;
import com.resturant.services.ItemsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenusController {

    @Autowired
    private ItemsServices itemsServices;

    @PostMapping
    public ResponseEntity<?> createMenues(@RequestBody Item item)throws Exception{
            return ResponseEntity.ok(itemsServices.createMenu(item));
    }

    @PutMapping
    public ResponseEntity<?>  update(@RequestBody Item item) {
        return ResponseEntity.ok(itemsServices.updateMenu(item));
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(itemsServices.findAll());
    }


    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
            return ResponseEntity.ok(itemsServices.findByName(name));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            itemsServices.delete(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch (Exception e){
            throw new ErrorNotFoundException("It's already deleted");
        }

    }


}
