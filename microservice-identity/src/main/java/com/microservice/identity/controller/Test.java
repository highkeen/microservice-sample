package com.microservice.identity.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

	@GetMapping
	public Map<String, Object> get(){
		return new HashMap<String, Object>();
	}
}
