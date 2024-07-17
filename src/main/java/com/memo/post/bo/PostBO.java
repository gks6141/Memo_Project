package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	//input: 로그인된 사람의 userId
	//output: List<Post>
	public List<Post> getPostListByUserId(Integer userId){
		return postMapper.getPostListByUserId(userId);
	}
	
	//input: 로그인된 사람의 userId
	//output: X
	public void addPostByUserId(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		String imagePath = null;
		
		if(file != null) {
			//업로드할 이미지가 있을때에만 업로드
			imagePath = fileManagerService.uploadFile(file, userLoginId);
		}
		
		postMapper.insertPostByUserId(userId,subject,content, imagePath);
	}
}
