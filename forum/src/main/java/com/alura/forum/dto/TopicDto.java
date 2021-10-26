package com.alura.forum.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alura.forum.model.Answer;
import com.alura.forum.model.Status;
import com.alura.forum.model.Topic;

public class TopicDto {

	private String category;

	private String description;

	private LocalDate openingDate;

	private UserDto userRequest;

	private UserDto userSolved;

	private Status status;

	private List<AnswerDto> answers;

	public TopicDto() {

	}

	public TopicDto(Topic topic) {

		UserDto userDtoRequest = new UserDto(topic.getUserRequest());
		UserDto userDtoSolved = new UserDto(topic.getUserSolved());
		
		AnswerDto answerDto = new AnswerDto();
		List<Answer> answers = topic.getAnswers();

		this.category = topic.getCategory();
		this.description = topic.getDescription();
		this.openingDate = topic.getOpeningDate();
		this.status = topic.getStatus();
		this.userRequest = userDtoRequest;
		this.userSolved = userDtoSolved;
		this.answers 	= answerDto.toAnswer(answers);

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

	public UserDto getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(UserDto userRequest) {
		this.userRequest = userRequest;
	}

	public UserDto getUserSolved() {
		return userSolved;
	}

	public void setUserSolved(UserDto userSolved) {
		this.userSolved = userSolved;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDto> answersDtos) {
		this.answers = answersDtos;
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
