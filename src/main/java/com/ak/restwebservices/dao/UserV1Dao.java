package com.ak.restwebservices.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.ak.restwebservices.bean.UserV1;

@Configuration
public class UserV1Dao {
	
	private static List<UserV1> users = new ArrayList<UserV1> ();
	private static int count = 0;
	
	static {
		users.add(new UserV1(1, "Anand", "1@email.com", "4567890001"));
		users.add(new UserV1(2, "Sathish", "2@email.com", "4567890002"));
		users.add(new UserV1(3, "Raj", "3@emil.com", "4567890003"));
		users.add(new UserV1(4, "Hari", "4@email.com", "4567890004"));
		count = users.size();
	}
	
	public UserV1 save(UserV1 newUser) {
		count = users.size();
		newUser.setId(++count);
		users.add(newUser);
		return newUser;
	}
	
	public List<UserV1> findAll() {
		return users;
	}
	
	public UserV1 findOne(int id) {
		for(UserV1 user : users) {
			if(user != null && user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public UserV1 remove(int id) {
		Iterator<UserV1>  usetIterator = users.iterator();
		UserV1 userToBeRemoved = null;
		while(usetIterator.hasNext()) {
			UserV1 user = usetIterator.next();
			if(user != null && user.getId() == id) {
				userToBeRemoved = user;
				usetIterator.remove();
				break;
			}
		}
		return userToBeRemoved;
	}

}
