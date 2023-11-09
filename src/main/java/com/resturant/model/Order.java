package com.resturant.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String status;
	private String address;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime created_at;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime update_at;

	@ManyToOne
	@JoinColumn(name = "resturant_id")
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "Order_items",
			joinColumns = @JoinColumn(name = "restaurant_order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> items;




}
