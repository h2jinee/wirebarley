package com.example.demo.test.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.test.model.Member;
import com.example.demo.test.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public Object test() {
		return "Hello World!";
	}
	
	@GetMapping("/searchMember")
	public List<Member> searchMember(HttpServletRequest request, @ModelAttribute Member member) throws Exception {
		return memberService.searchMember(request, member);
	}
	
	@PostMapping("/insertMember")
	@ResponseBody
	public String insertMember(@RequestBody Map<String, Object> map) throws Exception {
		JSONObject rData = new JSONObject();
		memberService.insertMember(map);
		rData.put("msg", "회원등록이 완료되었습니다.");
		
		return rData.toString();
	}
	
	@PutMapping("/updatePassword")
	@ResponseBody
	public String updatePassword(@RequestBody Map<String, Object> map) throws Exception {
		JSONObject rData = new JSONObject();
		
		if(map.get("id") == null) {
			rData.put("msg", "비밀번호를 변경할 id를 입력해주세요.");
		} else {
			memberService.updatePassword(map);
			rData.put("msg", "비밀번호가 성공적으로 변경되었습니다.");
		}
		
		return rData.toString();
	}
	
	@DeleteMapping("/deleteMember")
	public String deleteMember(HttpServletRequest request, @RequestParam String memberId) throws Exception {
		JSONObject rData = new JSONObject();
		memberService.deleteMember(request, memberId);
		rData.put("msg", "회원삭제가 완료되었습니다.");
		
		return rData.toString();
	}
	
}