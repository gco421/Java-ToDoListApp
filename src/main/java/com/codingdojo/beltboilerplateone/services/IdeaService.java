package com.codingdojo.beltboilerplateone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.beltboilerplateone.models.Idea;
import com.codingdojo.beltboilerplateone.repositories.IdeaRepository;


@Service
public class IdeaService{
	private IdeaRepository ideaRepository;

	public IdeaService(IdeaRepository ideaRepository) {
	    this.ideaRepository = ideaRepository;
		}
	
    // creates an idea
    public Idea createidea(Idea i) {
        return (Idea) ideaRepository.save(i);
    }
    
    // returns all the ideas
    public List<Idea> allIdeas() {
        return (List<Idea>) ideaRepository.findAll();
    }

    public Idea findIdeaById(Long id) {
    	Idea obj = ideaRepository.findById(id).get();
    	return obj;
    }
    
	// retrieves idea
	public Idea findIdea(Long id) {
	     Optional<Idea> optionalIdea = ideaRepository.findById(id);
	     if(optionalIdea.isPresent()) {
	         return optionalIdea.get();
	     } else {
	         return null;
	     }
	}
	
	public void deleteIdea(Long id) {
		// TODO Auto-generated method stub
		ideaRepository.deleteById(id);
	}
	
	public void updateIdea(Idea idea) {
		// TODO Auto-generated method stub
		ideaRepository.save(idea);
	}
	
}
