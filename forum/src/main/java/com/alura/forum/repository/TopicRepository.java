package com.alura.forum.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.forum.model.Topic;

@org.springframework.stereotype.Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Optional<Topic> findById(Long id);
	
	Topic getById(Long id);

	List<Topic> findAll();
	
}
