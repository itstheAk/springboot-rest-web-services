package com.ak.restwebservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {

	@Autowired
	private UserDao userDaoService;
	
	@GetMapping(path = "/users")
	public MappingJacksonValue findAllUsers() {
		List<User> actualUsers = userDaoService.findAll();
		
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email","phone");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilter", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(actualUsers);
		filteredData.setFilters(filter);
		
		return filteredData;
	}
	
	@GetMapping(path = "/users/{id}")
	public MappingJacksonValue findUser(@PathVariable(name = "id") int id) {
		User user =  userDaoService.findOne(id);
		if(user == null) {
			throw new NotFoundException(id + " Not found");
		}
		
		Resource<User> userResource = new Resource<>(user);
		ControllerLinkBuilder linkTo= ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
		userResource.add(linkTo.withRel("all-users"));
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email","phone");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilter", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(userResource);
		filteredData.setFilters(filter);
		
		return filteredData;
		
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
