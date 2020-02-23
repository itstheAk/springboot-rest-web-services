package com.ak.restwebservices.controller;

import java.lang.management.ManagementFactory;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PingController {
	
	@Autowired
	private MessageSource msgSource;
	
	@GetMapping(path = "/system-info")
	public Map<String, String> getSystemInfo() {
		return ManagementFactory.getRuntimeMXBean().getSystemProperties();
    }
	
	@GetMapping(path = "/greet/{name}")
	public String greet(@PathVariable(name = "name") String userName) {
		return String.format("Hello! %s", userName);
	}
	
	@GetMapping(path = "/greet-int/{name}")
	public String greetInternatioally(@PathVariable(name = "name") String userName) {
		return msgSource.getMessage("greet-message", new String[] {userName}, LocaleContextHolder.getLocale());
	}

}
