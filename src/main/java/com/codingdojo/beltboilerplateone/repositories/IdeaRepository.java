package com.codingdojo.beltboilerplateone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltboilerplateone.models.Idea;


@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{
	// this method retrieves all the ideas from the database
    List<Idea> findAll(); 
}