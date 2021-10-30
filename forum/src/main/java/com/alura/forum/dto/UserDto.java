package com.alura.forum.dto;

import com.alura.forum.model.User;

public class UserDto {

	private String name;

	private Integer age;

	private String email;

	public UserDto() {

	}

	public UserDto(User user) {
		this.name = user.getName();
		this.age = user.getAge();
		this.email = user.getEmail();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", age=" + age + ", email=" + email + "]";
	}

}
