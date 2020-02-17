package com.ak.restwebservices.controller;

import java.lang.management.ManagementFactory;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingController {
	
	@GetMapping(path = "/system-info")
	public Map<String, String> getSystemInfo() {
		return ManagementFactory.getRuntimeMXBean().getSystemProperties();
    }
	
	@GetMapping(path = "/greet/{name}")
	public String greet(@PathVariable(name = "name") String userName) {
		return String.format("Hello! %s", userName);
	}

}
