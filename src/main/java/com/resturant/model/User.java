package com.resturant.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;


@Data
@Entity
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String userName;
	private String password;

	@Email
	@Valid
	private String email;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate created_at;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm a")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate updated_at;

	
	
	
}
