package com.alura.forum.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.alura.forum.model.Answer;
import com.alura.forum.model.AnswerSolution;
import com.alura.forum.model.Category;
import com.alura.forum.model.Status;
import com.alura.forum.model.Topic;

public class TopicDto {

	private Category category;

	private String subCategory;

	private String description;

	private LocalDate openingDate;

	private UserDto userRequest;

	private Status status;

	private List<AnswerDto> answers;

	public TopicDto() {

	}

	public TopicDto(Topic topic) {

		UserDto userDtoRequest = new UserDto(topic.getUserRequest());

		AnswerDto answerDto = new AnswerDto();
		List<Answer> answers = topic.getAnswers();

		this.category = topic.getCategory();
		this.subCategory = topic.getSubCategory();
		this.description = topic.getDescription();
		this.openingDate = topic.getOpeningDate();
		this.userRequest = userDtoRequest;
		this.answers = answerDto.toAnswer(answers);
		this.status = isSolved(this.answers);

	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}

	public List<TopicDto> toTopic(List<Topic> topic) {

		List<TopicDto> list = new ArrayList<>();

		for (int i = 0; i < topic.size(); i++) {

			TopicDto topicDto = new TopicDto(topic.get(i));
			list.add(topicDto);

		}

		return list;

	}

	public Status isSolved(List<AnswerDto> answers) {

		Status status = null;

		if (answers.size() != 0) {

			for (int i = 0; i < answers.size(); i++) {

				if (answers.get(i).getAnswerSolution() == AnswerSolution.YES) {
					status = Status.SOLVED;
				}

				else {
					status = Status.OPEN;
				}
			}
		} else {
			status = Status.NO_REPLY;
		}
		return status;

	}

}
