package com.ak.restwebservices.bean;

import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties(value = {"email"})
@JsonFilter("UserBeanFilterV2")
public class UserV2 {
	
	private int id;
	@Size(min = 2, max = 30, message = "Invalid Name")
	private String name;
	private int age;
	private List<Contact> contacts;
	
	public UserV2(int id, String name, int age, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserV2 other = (UserV2) obj;
		if (age != other.age)
			return false;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserV2 [id=" + id + ", name=" + name + ", age=" + age + ", contacts=" + contacts + "]";
	}

	
	
	
	
}
