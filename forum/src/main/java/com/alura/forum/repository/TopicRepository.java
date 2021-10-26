package com.alura.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.alura.forum.model.Topic;

@org.springframework.stereotype.Repository
public interface TopicRepository extends Repository<Topic, Long> {

	Topic findById(Long id);

	List<Topic> findAll();

}
