package com.example.demo.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.TestDao;
import com.example.demo.test.dto.Employee;
import com.example.demo.test.dto.Shop;

@Service
public class TestService {
	
	@Autowired
	TestDao testDao;
	
	public Map<String, Object> getShopInfo(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Employee> employees = testDao.getEmployeeList();
		
		map.put("shop", testDao.getShopInfo());
		map.put("employee", employees);
		return map;
	}
	
	public List<Employee> getEmployeeList(){
		List<Employee> employees = testDao.getEmployeeList();
		return employees;
	}
	
	public boolean insertShop(Shop s) {
		return testDao.addShop(s);
	}
	
	public boolean insertEmployee(Employee e) {
		return testDao.addEmployee(e);
	}
	
	public boolean deleteShop(Shop p) {
		return testDao.deleteShop(p);
	}
}
