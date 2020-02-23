package com.ak.restwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootRestWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver getLocale() {
		SessionLocaleResolver locale = new SessionLocaleResolver();
		locale.setDefaultLocale(Locale.US);
		return locale;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setBasename("messages");
		return msgSource;
	}

}
