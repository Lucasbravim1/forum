package com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forum.dto.RegisterTopicDto;
import com.alura.forum.dto.TopicDto;
import com.alura.forum.model.Topic;
import com.alura.forum.model.User;
import com.alura.forum.repository.TopicRepository;
import com.alura.forum.repository.UserRepository;

@RestController
@RequestMapping(value = "/topic")
public class TopicRestController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> searchTopic(@RequestParam(value = "id") @Valid Long id) {

		Optional<Topic> topic = topicRepository.findById(id);

		if (!topic.isEmpty()) {
			TopicDto topicDto = new TopicDto(topic.get());
			return ResponseEntity.ok(topicDto);
		}
		return new ResponseEntity<>("Topic Not Found !", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/search/all", method = RequestMethod.GET)
	public List<TopicDto> searchAllTopics(@RequestParam String orderBy, @RequestParam Integer page, @RequestParam Integer quantity) {
		
		Pageable pageable = PageRequest.of(page, quantity, Direction.DESC, orderBy);

		Page<Topic> list = topicRepository.findAll(pageable);
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

	@RequestMapping(value = "/delete{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTopic(@RequestParam Long id) {

		Optional<Topic> topic = topicRepository.findById(id);
		if (!topic.isEmpty()) {
			topicRepository.delete(topic.get());
			return new ResponseEntity<String>("Topic has been deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No Topic Found", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTopic(@RequestBody @Valid RegisterTopicDto registerTopicDto, @RequestParam Long id) {

		Optional<Topic> optionalTopic = topicRepository.findById(id);
		if (!optionalTopic.isEmpty()) {
			Topic topic = optionalTopic.get();
			topic.setCategory(registerTopicDto.getCategory());
			topic.setDescription(registerTopicDto.getDescription());
			topic.setSubCategory(registerTopicDto.getSubCategory());

			topicRepository.save(topic);
			return new ResponseEntity<String>("Topic updated successfully", HttpStatus.OK);
		}

		else {
			return new ResponseEntity<String>("Topic with id = " + id + " not found", HttpStatus.NOT_FOUND);

		}
	}
}
