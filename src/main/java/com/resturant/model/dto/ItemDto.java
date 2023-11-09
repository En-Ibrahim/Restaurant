package com.resturant.model.dto;

import com.resturant.model.Item;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private String discription;
    private double prices;

    private LocalDate created_at;
    private LocalDate update_at;

    public static ItemDto mapToItemDto(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDiscription(item.getDiscription());
        itemDto.setPrices(item.getPrices());
        itemDto.setCreated_at(item.getCreated_at());
        itemDto.setUpdate_at(item.getUpdate_at());
        return itemDto;
    }


}
