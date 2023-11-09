package com.resturant.services;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.Restaurant;
import com.resturant.model.dto.MenuDto;
import com.resturant.repo.ItemsRepo;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.repo.ResturantsRepo;
@Service
public class ResturantServices {

	@Autowired
	private ResturantsRepo resturantsRepo;
	@Autowired
	private ItemsRepo itemsRepo;
	
	public Restaurant createResturant(Restaurant restaurant) {
		Optional<Restaurant> saveRestaurant= resturantsRepo.findByName(restaurant.getName());
		if(saveRestaurant.isPresent())
			throw  new DuplicateRequestException("This Restaurant is already exited");
		restaurant.setCreated_at(LocalDate.now());
		restaurant.setUpdate_at(LocalDate.now());
		return resturantsRepo.save(restaurant);
	}


	public Restaurant updateResturant(Restaurant entity) {
		Restaurant restaurant = resturantsRepo.findById(entity.getId()).orElseThrow(
				() -> {throw new NoSuchElementException("not found this");}
		);
		restaurant.setCreated_at(restaurant.getCreated_at());
		restaurant.setUpdate_at(LocalDate.now());
		return resturantsRepo.save(restaurant);
	}
	
	
	public List<Restaurant> findAll(){
		return resturantsRepo.findAll();
	}
	
	public Restaurant findById(Long id) {
		return resturantsRepo.findById(id).orElseThrow(
				()-> {throw new ErrorNotFoundException("Not found Restaurant with ID : "+id);
				}
		);
	}
	
	public MenuDto findAllMenusByResturantId(Long  id){
		return MenuDto.MapToMenuDto(itemsRepo.findAllByRestaurantId(id)) ;
	}

	public void deleteById(Long id){
		resturantsRepo.deleteById(id);
	}
	
}
