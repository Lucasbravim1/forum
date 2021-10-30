package com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forum.dto.RegisterTopicDto;
import com.alura.forum.dto.TopicDto;
import com.alura.forum.model.Topic;
import com.alura.forum.model.User;
import com.alura.forum.repository.TopicRepository;
import com.alura.forum.repository.UserRepository;

@RestController
@RequestMapping(value = "topic")
public class TopicRestController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public TopicDto searchTopic(@PathVariable(value = "id") Long id) {

		Optional<Topic> topic = topicRepository.findById(id);

		if (!topic.isEmpty()) {
			TopicDto topicDto = new TopicDto(topic.get());
			return topicDto;
		}
		return null;

	}

	@RequestMapping(value = "/search/all", method = RequestMethod.GET)
	public List<TopicDto> searchAllTopics() {

		List<Topic> list = topicRepository.findAll();
		TopicDto topicDto = new TopicDto();

		return topicDto.toTopicDto(list);

	}

	@RequestMapping(value = "/register{id}", method = RequestMethod.POST)
	public ResponseEntity<TopicDto> registerTopic(@RequestBody @Valid RegisterTopicDto registerTopicDto,
			UriComponentsBuilder uriComponentsBuilder) {

		TopicDto topicDto = new TopicDto(registerTopicDto);
		User user = userRepository.findByEmail(topicDto.getUserRequest().getEmail());

		if (user != null) {
			Topic topic = new Topic(topicDto, user);
			topicRepository.save(topic);

			URI uri = uriComponentsBuilder.path("topic/register").buildAndExpand(topic.getId()).toUri();
			return ResponseEntity.created(uri).body(topicDto);
		} else {
			return ResponseEntity.badRequest().body(topicDto);
		}

	}

}
