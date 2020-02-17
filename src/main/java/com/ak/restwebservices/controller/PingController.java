package com.ak.restwebservices.controller;

import java.lang.management.ManagementFactory;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingController {
	
	@GetMapping(path = "/ping-details")
	public Map<String, String> getSystemInfo() {
		return ManagementFactory.getRuntimeMXBean().getSystemProperties();
    }

}
