package com.resturant.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Restaurant {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String name;
	private String discription;
	private String location;


	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate created_at;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate update_at;


	@OneToMany(mappedBy = "restaurant")
	@JsonIgnore
	private List<Item> items;

	@OneToMany(mappedBy = "restaurant")
	@JsonIgnore
	private List<Order> orders;


}
