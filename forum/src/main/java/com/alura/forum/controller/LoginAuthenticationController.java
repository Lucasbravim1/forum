package com.alura.forum.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.dto.TokenDto;
import com.alura.forum.dto.UserAuthenticationDto;
import com.alura.forum.model.User;
import com.alura.forum.repository.UserRepository;
import com.alura.forum.service.TokenService;

@RestController
@RequestMapping(value = "/login")
public class LoginAuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserAuthenticationDto userAuthenticationDto) {
//
//		String email = userAuthenticationDto.getEmail();
//		String userPassword = userAuthenticationDto.getPassword();
//
//		Optional<User> user = userRepository.findByEmail(email);
//
//		if (user.isPresent()) {
//			if (userAuthenticationDto.validatePassword(userPassword, user.get().getPassword())) {
//				return new ResponseEntity<String>("The user is authenticated !", HttpStatus.OK);
//			} else {
//				return new ResponseEntity<String>("The user password is Wrong !", HttpStatus.UNAUTHORIZED);
//			}
//		} else {
//			return new ResponseEntity<String>("User not found !", HttpStatus.NOT_FOUND);
//		}
//	}

	// forma mais elegante --> usando Token
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUserToken(@RequestBody @Valid UserAuthenticationDto userAuthenticationDto) {
		UsernamePasswordAuthenticationToken dataUser = userAuthenticationDto.getCredencials();
		try {
			Authentication authenticate = authenticationManager.authenticate(dataUser);
			String token = tokenService.generateToken(authenticate);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return new ResponseEntity<String>("User not found !", HttpStatus.NOT_FOUND);
		}

	}

}
