package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	//@Autowired
	//UserRepository userRepository;

	  @Autowired MongoTemplate mongoTemplate;
	 
	
	
	@RequestMapping(value="/createUser",method = RequestMethod.POST)
	@ResponseBody User createUser(@RequestBody User user){
		
		mongoTemplate.save(user);
		return user;
		
	}
	
	

}
