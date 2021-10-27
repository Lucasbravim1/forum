package com.alura.forum.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.forum.model.Answer;
import com.alura.forum.model.AnswerSolution;

public class AnswerDto {

	private String respond;

	private LocalDate date;

	private UserDto userDto;

	private AnswerSolution answerSolution;

	public AnswerDto() {

	}

	public AnswerDto(Answer answer) {

		UserDto userDto = new UserDto(answer.getUser());

		this.respond = answer.getRespond();
		this.date = answer.getDate();
		this.userDto = userDto;
		this.answerSolution = answer.getAnswerSolution();
	}

	public String getRespond() {
		return respond;
	}

	public void setRespond(String respond) {
		this.respond = respond;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public UserDto getUser() {
		return userDto;
	}

	public void setUser(UserDto user) {
		this.userDto = user;
	}

	public AnswerSolution getAnswerSolution() {
		return answerSolution;
	}

	public void setAnswerSolution(AnswerSolution answerSolution) {
		this.answerSolution = answerSolution;
	}

	public List<AnswerDto> toAnswer(List<Answer> answers) {

		List<AnswerDto> list = new ArrayList<>();

		for (int i = 0; i < answers.size(); i++) {

			AnswerDto answerDto = new AnswerDto(answers.get(i));
			list.add(answerDto);
		}

		return list;
	}

}
