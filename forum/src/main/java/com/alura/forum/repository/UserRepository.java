package com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.forum.model.User;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
