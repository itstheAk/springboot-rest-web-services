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

import com.ak.restwebservices.bean.UserV1;
import com.ak.restwebservices.bean.UserV2;
import com.ak.restwebservices.dao.UserV1Dao;
import com.ak.restwebservices.dao.UserV2Dao;
import com.ak.restwebservices.exception.NotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {

	@Autowired
	private UserV1Dao userV1DaoService;
	
	@Autowired
	private UserV2Dao userV2DaoService;
	
	@GetMapping(path = "/v1/users")
	public MappingJacksonValue findAllUsersV1() {
		List<UserV1> actualUsers = userV1DaoService.findAll();
		
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email","phone");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilterV1", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(actualUsers);
		filteredData.setFilters(filter);
		
		return filteredData;
	}
	
	@GetMapping(path = "/v1/users/{id}")
	public MappingJacksonValue findUserV1(@PathVariable(name = "id") int id) {
		UserV1 user =  userV1DaoService.findOne(id);
		if(user == null) {
			throw new NotFoundException(id + " Not found");
		}
		
		Resource<UserV1> userResource = new Resource<>(user);
		ControllerLinkBuilder linkTo= ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsersV1());
		userResource.add(linkTo.withRel("all-users"));
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email","phone");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilterV1", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(userResource);
		filteredData.setFilters(filter);
		
		return filteredData;
		
	}
	
	@PostMapping(path = "/v1/users")
	public ResponseEntity<Object> createUserV1(@Valid @RequestBody UserV1 user) {
		userV1DaoService.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
	}
	
	@DeleteMapping(path = "/v1/users/{id}")
	public void removeUserV1(@PathVariable(name = "id") int id) {
		UserV1 removedUser = userV1DaoService.remove(id);
		
		if(removedUser == null) {
			throw new NotFoundException(id + " Not found");
		}
	}
	
	@GetMapping(path = "/v2/users")
	public MappingJacksonValue findAllUsersV2() {
		List<UserV2> actualUsers = userV2DaoService.findAll();
		
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","contacts");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilterV2", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(actualUsers);
		filteredData.setFilters(filter);
		
		return filteredData;
	}
	
	@GetMapping(path = "/v2/users/{id}")
	public MappingJacksonValue findUserV2(@PathVariable(name = "id") int id) {
		UserV2 user =  userV2DaoService.findOne(id);
		if(user == null) {
			throw new NotFoundException(id + " Not found");
		}
		
		Resource<UserV2> userResource = new Resource<>(user);
		ControllerLinkBuilder linkTo= ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsersV2());
		userResource.add(linkTo.withRel("all-users"));
		
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","contacts");
		FilterProvider filter = new SimpleFilterProvider().addFilter("UserBeanFilterV2", userFilter);
		
		MappingJacksonValue filteredData = new MappingJacksonValue(userResource);
		filteredData.setFilters(filter);
		
		return filteredData;
		
	}
	
	@PostMapping(path = "/v2/users")
	public ResponseEntity<Object> createUserV2(@Valid @RequestBody UserV2 user) {
		userV2DaoService.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
	}
	
	@DeleteMapping(path = "/v2/users/{id}")
	public void removeUserV2(@PathVariable(name = "id") int id) {
		UserV2 removedUser = userV2DaoService.remove(id);
		
		if(removedUser == null) {
			throw new NotFoundException(id + " Not found");
		}
	}
}
