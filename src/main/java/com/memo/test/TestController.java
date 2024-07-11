package com.memo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.memo.post.mapper.PostMapper;

@Controller
public class TestController {
	@Autowired
	PostMapper postMapper;
	
	
	@ResponseBody
	@RequestMapping("/1")
	public String test1() {
		return "성공";
	}
	
	@ResponseBody
	@RequestMapping("/2")
	public Map<String,String> test2() {
		Map<String, String> list = new HashMap<>();
		list.put("result", "성공");
		return list;
	}
	
	@RequestMapping("/3")
	public String test3() {
		return "test/test3";
	}
	
	@ResponseBody
	@GetMapping("/4")
	public List<Map<String, Object>> test4(){
		return postMapper.selectPost();
	}
	
}
