package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글쓰기 API
	 * @param subject
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required = false) MultipartFile file,
			HttpSession session){
		
		//글쓰이 번호를 session에서 꺼낸다
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		//DB insert요청
		postBO.addPostByUserId(userId, userLoginId, subject,content,file);
		
		Map<String,Object> result = new HashMap<>();
		result.put("code",200);
		result.put("result", "성공");
		
//		if(post.equals("성공")) {
//			result.put("code",200);
//			result.put("result", "성공");
//		} else {
//			result.put("code",500);
//			result.put("error_message", "DB입력 실패");
//		}
		
		return result;
	}
	
	/**
	 * 글수정 API
	 * @param postId
	 * @param subject
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session){
		
		//userId
		int userId = (int)session.getAttribute("userId");
		String  userLoginId = (String)session.getAttribute("userLoginId");
		
		//DB update
		postBO.updatePostByPostId(userId, userLoginId, postId, subject, content, file);
		//응답
		Map<String ,Object> result = new HashMap<>();
		result.put("code",200);
		result.put("result", "성공");
		return result;
	}
	
	/**
	 * 삭제 API
	 * @param postId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		//user
		int userId = (int)session.getAttribute("userId");
		
		//DB Delete
		postBO.deletePostByPostId(userId, postId);
		
		//response
		Map<String, Object> result = new HashMap<>();
		result.put("code",200);
		result.put("result", "성공");
		return result;
		
	}
	
	
}
