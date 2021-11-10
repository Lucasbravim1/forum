package com.alura.forum.dto;

import javax.validation.constraints.NotNull;

import com.alura.forum.model.UserProfile;

public class RegisterUserDto {

	@NotNull
	private String name;

	@NotNull
	private String password;

	@NotNull
	private Integer age;

	@NotNull
	private String email;
	
	private UserProfile userProfile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "RegisterUserDto [name=" + name + ", password=" + password + ", age=" + age + ", email=" + email
				+ ", userProfile=" + userProfile + "]";
	}

}
