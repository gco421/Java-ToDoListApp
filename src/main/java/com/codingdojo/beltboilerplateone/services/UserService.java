package com.codingdojo.beltboilerplateone.services;

import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.codingdojo.beltboilerplateone.models.User;
import com.codingdojo.beltboilerplateone.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
    	System.out.println("I'm in UserService in User findByEmail method");
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
    	System.out.println("I'm in UserService-authenticateUser at the top");
        User user = userRepository.findByEmail(email);
        System.out.println("I'm in UserService-authenticateUser after userRepository.findByEmail");
        // if we can't find it by email, return false
        if(user == null) {
        	System.out.println("I'm after if(user == null)");
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
            	System.out.println("I'm after if(BCrypt.checkpw(password, user.getPassword");
                return true;
            } else {
            	System.out.println("I'm after the else for if(BCrypt.checkpw(password, user.getPassword");
                return false;
            }
        }
    }

//	public List<User> findAllUsers() {
//		// TODO Auto-generated method stub
//		return userRepository.findAllUsers();
//	}
}
