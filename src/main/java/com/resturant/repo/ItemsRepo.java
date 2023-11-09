package com.resturant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemsRepo extends JpaRepository<Item, Long> {



    List<Item> findAllByRestaurantId(Long id);

    Optional<Item> findByName(String name);
}
