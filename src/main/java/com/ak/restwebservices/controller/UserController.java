package com.ak.restwebservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ak.restwebservices.bean.User;
import com.ak.restwebservices.dao.UserDao;
import com.ak.restwebservices.exception.NotFoundException;

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
		User user =  userDaoService.findOne(id);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new NotFoundException(id + " Not found");
		}
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		userDaoService.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void removeUser(@PathVariable(name = "id") int id) {
		User removedUser = userDaoService.remove(id);
		
		if(removedUser == null) {
			throw new NotFoundException(id + " Not found");
		}
	}
}
