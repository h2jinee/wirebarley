package com.example.demo.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.test.model.Member;

public interface MemberService {
	
	List<Member> searchMember(HttpServletRequest request, Member member) throws Exception;
	
	void insertMember(Map<String, Object> map) throws Exception;

    void updatePassword(Map<String, Object> map) throws Exception;

    void deleteMember(HttpServletRequest request, String memberId) throws Exception;
}
