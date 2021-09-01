package com.example.demo.test.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shop {
	private Long shopSeq;
	private String name;
	private String businessNumber;
	private String tel;
	private String kakaoId;
	private String status;
	
	@JsonBackReference
	private List<Employee> employee;

}
