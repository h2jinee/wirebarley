package com.example.demo.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.model.Member;

@Mapper
public interface MemberMapper {
	
	List<Member> searchMember(Member member) throws Exception;
	
	int insertMember(Map<String, Object> map) throws Exception;

    int updatePassword(Map<String, Object> map) throws Exception;

    int deleteMember(String memberId) throws Exception;
}
