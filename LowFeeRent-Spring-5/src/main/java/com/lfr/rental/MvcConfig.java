package com.lfr.rental;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/admin").setViewName("admin");
		registry.addViewController("/apartment/*").setViewName("apartment");
		registry.addViewController("/person/*").setViewName("person");
		registry.addViewController("/booking/*").setViewName("booking");
		registry.addViewController("/fillin/*").setViewName("fillin");
	}

}