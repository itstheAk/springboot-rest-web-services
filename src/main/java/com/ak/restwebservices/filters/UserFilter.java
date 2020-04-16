package com.ak.restwebservices.filters;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.ak.restwebservices.bean.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class UserFilter {
	
	public static MappingJacksonValue getFilteredUser(User actualUser) {
		MappingJacksonValue filteredData = null;
		try {
			filteredData = new MappingJacksonValue(actualUser);
			
			SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email","phone");
			FilterProvider filter = new SimpleFilterProvider().addFilter("userFilter", userFilter);
			
			filteredData.setFilters(filter);
			
		} catch (Exception e) {
			
		}
		return filteredData;
	}
}
