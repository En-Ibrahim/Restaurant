package com.resturant.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.resturant.error.DublicateException;
import com.resturant.error.ErrorNotFoundException;
import com.resturant.model.Item;
import com.resturant.model.Restaurant;
import com.resturant.model.dto.ItemDto;
import com.resturant.model.dto.MenuDto;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.repo.ItemsRepo;

@Service
public class ItemsServices {

	@Autowired
	private ItemsRepo itemsRepo;
	
	
	public ItemDto createMenu(Item item) {

		Optional<Item> saveItem = itemsRepo.findByName(item.getName());
			if(saveItem.isPresent())
				throw new DublicateException("This item is already exited");
			item.setCreated_at(LocalDate.now());
			item.setUpdate_at(LocalDate.now());
			return ItemDto.mapToItemDto(itemsRepo.save(item));
	}

	public Item updateMenu(Item item){
		Item menu = itemsRepo.findById(item.getId()).orElseThrow(
				() -> new ErrorNotFoundException("not found item")
		);
		item.setCreated_at(item.getCreated_at());
		item.setUpdate_at(LocalDate.now());
		return itemsRepo.save(item);
	}
	
	public List<MenuDto> findAll(){
		List<Item> items= itemsRepo.findAll();
		Map<Restaurant,List<Item>> restaurantItemMap= items.stream()
				.collect(Collectors.groupingBy(Item::getRestaurant));
		return	restaurantItemMap.entrySet().stream().map(
				entry ->{
					Restaurant restaurant = entry.getKey();
					List<Item> restaurantItems= entry.getValue();
					MenuDto menuDto= MenuDto.MapToMenuDto(restaurantItems);
					menuDto.setRestaurant(restaurant);
					return menuDto;
				}).collect(Collectors.toList());
	}
	
	public ItemDto findByName(String name){
		Item item= itemsRepo.findByName(name).orElseThrow(
				()-> new ErrorNotFoundException("Not found Items")
		);
		return ItemDto.mapToItemDto(item);
	}

	public void delete(Long id){
		itemsRepo.deleteById(id);
	}
	
	
}
