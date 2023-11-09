package com.resturant.repo;

import com.resturant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResturantsRepo extends JpaRepository<Restaurant, Long>{

    Optional<Restaurant> findByName(String name);

}
