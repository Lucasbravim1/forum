package com.alura.forum.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.alura.forum.model.Topic;

@org.springframework.stereotype.Repository
public interface TopicRepository extends Repository<Topic,Long>{

	 Optional<Topic> findById(Long id);

}
