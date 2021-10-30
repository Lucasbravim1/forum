package com.alura.forum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alura.forum.model.Category;

public class RegisterTopicDto {

	private Category category;

	@NotBlank
	@NotNull
	private String subCategory;

	@NotBlank
	@NotNull
	private String description;

	private UserDto userRequest;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDto getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(UserDto userRequest) {
		this.userRequest = userRequest;
	}

	@Override
	public String toString() {
		return "RegisterTopicDto [category=" + category + ", subCategory=" + subCategory + ", description="
				+ description + ", userRequest=" + userRequest + "]";
	}

}
