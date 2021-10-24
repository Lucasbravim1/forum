package com.alura.forum.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.model.Topic;
import com.alura.forum.repository.TopicRepository;

@RestController
public class TopicRestController {

	@Autowired
	private TopicRepository topicRepository;

	@RequestMapping(value = "topic/search/{id}", method = RequestMethod.GET)
	public Optional<Topic> searchTopic(@PathVariable(value = "id") Long id) {

		return topicRepository.findById(id);
	}
}
