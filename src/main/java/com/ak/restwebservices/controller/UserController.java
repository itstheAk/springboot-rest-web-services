package com.ak.restwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	public User findUser(@PathVariable(name = "id") int id) {
		return userDaoService.findOne(id);
	}
}
