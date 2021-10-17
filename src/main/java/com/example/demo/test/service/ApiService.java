package com.example.demo.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.test.api.ApiClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApiService {
	private final ApiClient apiClient;
	
	@Transactional(readOnly = true)
	public String findByCountry() {
		return apiClient.requestExchangeRate();
	}

}
