package com.example.demo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.test.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/api/v1/exchangeRate")
	public String get() {
		return apiService.findByCountry();
	}
	
}