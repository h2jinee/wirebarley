package com.example.demo.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.test.dto.Employee;
import com.example.demo.test.dto.Shop;
import com.example.demo.test.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/")
	public Object test() {
		return "Hello World!";
	}
	
	@GetMapping("/shop")
	public Map<String, Object> getShopInfo(){
		return testService.getShopInfo();
	}
	
	@GetMapping("/insertShop")
	@ResponseBody
	public boolean insertShop(@RequestBody Shop shop) {
		return testService.insertShop(shop);
	}
	
	@GetMapping("/insertEmployee")
	@ResponseBody
	public boolean insertEmployee(@RequestBody Employee employee) {
		return testService.insertEmployee(employee);
	}
	
	@DeleteMapping("/deleteShop")
	public boolean deleteShop(@RequestParam Shop seq) {
		
		return testService.deleteShop(seq);
	}
	
}