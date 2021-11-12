package com.alura.forum.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAuthenticationDto {

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean validatePassword(String userPassword, String encryptedPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(userPassword, encryptedPassword);
	}

	public UsernamePasswordAuthenticationToken getCredencials() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}

}
