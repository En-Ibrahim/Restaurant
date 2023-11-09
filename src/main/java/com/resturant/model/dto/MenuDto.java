package com.resturant.model.dto;

import com.resturant.model.Item;
import com.resturant.model.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDto {

    private Restaurant restaurant;
    private List<ItemDto> items= new ArrayList<>();

    public static MenuDto MapToMenuDto(List<Item> items){
        MenuDto menuDto= new MenuDto();
        items.forEach(item -> {
            menuDto.getItems().add(ItemDto.mapToItemDto(item));
        });
        if( menuDto.items.size()>0)
            menuDto.setRestaurant(items.get(0).getRestaurant());

        return menuDto;
    }


}
