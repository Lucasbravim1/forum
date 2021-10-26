package com.alura.forum.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alura.forum.model.Status;
import com.alura.forum.model.Topic;

public class TopicDto {

	private String category;

	private String description;

	private LocalDate openingDate;

	private String userRequest;

	private String userSolved;

	private Status status;

	public TopicDto() {

	}

	public TopicDto(Topic topic) {

		this.category = topic.getCategory();
		this.description = topic.getDescription();
		this.openingDate = topic.getOpeningDate();
		this.userRequest = topic.getUserRequest();
		this.userSolved = topic.getUserSolved();
		this.status = topic.getStatus();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public String getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}

	public String getUserSolved() {
		return userSolved;
	}

	public void setUserSolved(String userSolved) {
		this.userSolved = userSolved;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<TopicDto> toTopic(List<Topic> topic) {

		List<TopicDto> list = new ArrayList<>();

		for (int i = 0; i < topic.size(); i++) {

			TopicDto topicDto = new TopicDto(topic.get(i));
			list.add(topicDto);

		}

		return list;

	}

}
