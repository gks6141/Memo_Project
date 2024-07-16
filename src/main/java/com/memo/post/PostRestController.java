package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="imagePath", required = false) String imagePath,
			HttpSession session){
		
		Integer userId = (Integer)session.getAttribute("userId");
		String post = postBO.addPostByUserId(userId,subject,content,imagePath);
		
		Map<String,Object> result = new HashMap<>();
		if(post.equals("성공")) {
			result.put("code",200);
			result.put("result", "성공");
		} else {
			result.put("code",500);
			result.put("error_message", "DB입력 실패");
		}
		
		return result;
	}
}
