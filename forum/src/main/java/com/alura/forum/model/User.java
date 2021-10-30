package com.alura.forum.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alura.forum.dto.TopicDto;
import com.alura.forum.dto.UserDto;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String password;

	private Integer age;

	private String email;

	private LocalDate entryDate;

	private Integer solvedTopics;

	public User() {

	}

	public User(UserDto userDto) {
		this.name = userDto.getName();
		this.age = userDto.getAge();
		this.email = userDto.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public Integer getSolvedTopics() {
		return solvedTopics;
	}

	public void setSolvedTopics(Integer solvedTopics) {
		this.solvedTopics = solvedTopics;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", email=" + email
				+ ", entryDate=" + entryDate + ", solvedTopics=" + solvedTopics + "]";
	}


}
