package com.ak.restwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ak.restwebservices.bean.User;
import com.ak.restwebservices.dao.UserDao;

@RestController
public class UserController {

	@Autowired
	private UserDao userDaoService;
	
	@GetMapping(path = "/users")
	public List<User> findAllUsers() {
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public ResponseEntity<User> findUser(@PathVariable(name = "id") int id) {
		ResponseEntity<User> response;
		User user =  userDaoService.findOne(id);
		if(user != null) {
			response = new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			response = ResponseEntity.notFound().build();
		}
		return response;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		userDaoService.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
	}
}
