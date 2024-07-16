package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	//input: 로그인된 사람의 userId
	//output: List<Post>
	public List<Post> getPostListByUserId(Integer userId){
		return postMapper.getPostListByUserId(userId);
	}
	
	public String addPostByUserId(Integer userId, String subject, String content, String imagePath) {
		postMapper.getPostByUserId(userId,subject,content,imagePath);
		return "성공";
	}
}
