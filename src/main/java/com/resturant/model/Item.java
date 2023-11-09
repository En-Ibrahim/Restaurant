package com.resturant.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Entity
public class Item {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String name;
	private String discription;
	private double prices;


	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate created_at;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate update_at;


	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	

	
	
	

}
