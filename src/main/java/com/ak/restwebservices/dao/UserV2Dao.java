package com.ak.restwebservices.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.ak.restwebservices.bean.Contact;
import com.ak.restwebservices.bean.UserV2;

@Configuration
public class UserV2Dao {
	
	private static List<UserV2> users = new ArrayList<UserV2> ();
	private static int count = 0;
	
	static {
		users.add(new UserV2(1, "Anand", 30, Arrays.asList(new Contact[] {new Contact("Email","1@email.com"), new Contact("Mobile","4567890001")}) ));
		users.add(new UserV2(2, "Sathish", 31, Arrays.asList(new Contact[] {new Contact("Email","2@email.com"), new Contact("Mobile","4567890002")})));
		users.add(new UserV2(3, "Raj", 30, Arrays.asList(new Contact[] {new Contact("Email","3@email.com"), new Contact("Mobile","4567890003")})));
		users.add(new UserV2(4, "Hari", 30, Arrays.asList(new Contact[] {new Contact("Email","4@email.com"), new Contact("Mobile","4567890004")})));
		count = users.size();
	}
	
	public UserV2 save(UserV2 newUser) {
		count = users.size();
		newUser.setId(++count);
		users.add(newUser);
		return newUser;
	}
	
	public List<UserV2> findAll() {
		return users;
	}
	
	public UserV2 findOne(int id) {
		for(UserV2 user : users) {
			if(user != null && user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public UserV2 remove(int id) {
		Iterator<UserV2>  usetIterator = users.iterator();
		UserV2 userToBeRemoved = null;
		while(usetIterator.hasNext()) {
			UserV2 user = usetIterator.next();
			if(user != null && user.getId() == id) {
				userToBeRemoved = user;
				usetIterator.remove();
				break;
			}
		}
		return userToBeRemoved;
	}

}
