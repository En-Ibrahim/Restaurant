package com.resturant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.model.User;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User, Long>{

    Optional<User> findByUserName(String name);

    Optional<User> findByEmail(String email);


}
