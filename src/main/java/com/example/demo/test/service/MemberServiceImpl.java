package com.example.demo.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.mapper.MemberMapper;
import com.example.demo.test.model.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	public List<Member> searchMember(HttpServletRequest request, Member member) throws Exception {
		return memberMapper.searchMember(member);
	}
	
	public void insertMember(Map<String, Object> map) throws Exception {
		memberMapper.insertMember(map);
	}
	
	public void updatePassword(Map<String, Object> map) throws Exception {
		memberMapper.updatePassword(map);
	}
	
	public void deleteMember(HttpServletRequest request, String memberId) throws Exception {
		memberMapper.deleteMember(memberId);
	}
}
