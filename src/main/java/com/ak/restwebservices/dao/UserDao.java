package com.ak.restwebservices.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.ak.restwebservices.bean.User;

@Configuration
public class UserDao {
	
	private static List<User> users = new ArrayList<User> ();
	private static int count = 0;
	
	static {
		users.add(new User(1, "Anand", "1@email.com", "4567890001"));
		users.add(new User(2, "Sathish", "2@email.com", "4567890002"));
		users.add(new User(3, "Raj", "3@emil.com", "4567890003"));
		users.add(new User(4, "Hari", "4@email.com", "4567890004"));
		count = users.size();
	}
	
	public User save(User newUser) {
		newUser.setId(++count);
		users.add(newUser);
		return newUser;
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user != null && user.getId() == id) {
				return user;
			}
		}
		return null;
	}

}
