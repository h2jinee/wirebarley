package com.example.demo.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
	private Long employeeSeq;
	private String name;
	private String tel;
	private String kakaoId;
	private String status;
}