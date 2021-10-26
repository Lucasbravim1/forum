package com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.dto.TopicDto;
import com.alura.forum.model.Topic;
import com.alura.forum.repository.TopicRepository;

@RestController
public class TopicRestController {

	@Autowired
	private TopicRepository topicRepository;

	@RequestMapping(value = "topic/search/{id}", method = RequestMethod.GET)
	public TopicDto searchTopic(@PathVariable(value = "id") Long id) {

		Topic topic = topicRepository.findById(id);

		if (topic != null) {
			TopicDto topicDto = new TopicDto(topic);
			return topicDto;
		}

		return null;

	}

	@RequestMapping(value = "topic/search/all", method = RequestMethod.GET)
	public List<TopicDto> searchAllTopics() {

		List<Topic> list = topicRepository.findAll();
		TopicDto topicDto = new TopicDto();

		return topicDto.toTopic(list);

	}
}
