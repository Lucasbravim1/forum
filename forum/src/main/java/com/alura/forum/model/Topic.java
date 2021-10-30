package com.alura.forum.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alura.forum.dto.TopicDto;
import com.alura.forum.dto.UserDto;
import com.alura.forum.repository.UserRepository;

@Entity
@Table(name = "topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String subCategory;

	private String description;

	private LocalDate openingDate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User userRequest;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "topic")
	private List<Answer> answers;

	public Topic() {

	}

	public Topic(TopicDto topicDto, UserRepository userRepository) {

		this.category = topicDto.getCategory();
		this.subCategory = topicDto.getSubCategory();
		this.description = topicDto.getDescription();
		this.openingDate = topicDto.getOpeningDate();
		this.status = topicDto.getStatus();
		this.userRequest = userRepository.findByEmail(topicDto.getUserRequest().getEmail());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(User userRequest) {
		this.userRequest = userRequest;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", category=" + category + ", subCategory=" + subCategory + ", description="
				+ description + ", openingDate=" + openingDate + ", userRequest=" + userRequest + ", status=" + status
				+ ", answers=" + answers + "]";
	}

}
