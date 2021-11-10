package com.alura.forum.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forum.dto.RegisterUserDto;
import com.alura.forum.model.User;
import com.alura.forum.repository.UserProfileRepository;
import com.alura.forum.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<RegisterUserDto> registerUser(@RequestBody RegisterUserDto registerUserDto,
			UriComponentsBuilder uriComponentsBuilder) {

		User user = new User(registerUserDto, userProfileRepository);
		userRepository.save(user);

		URI uri = uriComponentsBuilder.path("/user/register").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(registerUserDto);

	}
}
