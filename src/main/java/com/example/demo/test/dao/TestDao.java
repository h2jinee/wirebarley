package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.test.dto.Employee;
import com.example.demo.test.dto.Shop;

@Repository
@Mapper
public class TestDao {
	
	@Autowired
	private SqlSession sqlSession;
	private String ns = "com.example.ProjectMapper.";
	
	public boolean addShop(Shop s) {
		if(s == null) {
			return false;
		}
		try {
			sqlSession.insert(ns + "insertShop", s); 
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addEmployee(Employee employee) {
		if(employee == null) {
			return false;
		}
		try {
			sqlSession.insert(ns + "insertShop", employee); 
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Shop getShopInfo() {
		return sqlSession.selectOne(ns + "getShopInfo");
	}
	
	public List<Employee> getEmployeeList() {
		return sqlSession.selectList(ns + "getEmployeeList");
	}
	
	public boolean deleteShop(Shop s) {
		try {
			sqlSession.delete(ns + "deleteShop", s);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
}
