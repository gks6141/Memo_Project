package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {
//	private Logger log = LoggerFactory.getLogger(PostBO.class);
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	//input: 로그인된 사람의 userId
	//output: List<Post>
	public List<Post> getPostListByUserId(Integer userId){
		return postMapper.getPostListByUserId(userId);
	}
	
	//input: postId, userId
	//output: Post or null
	
	public Post getPostByPostIdUserId(int userId, int postId) {
		return postMapper.selectPostByUserIdPostId(userId, postId);
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
	
	//input: 파라미터들
	//output: X
	public void updatePostByPostId(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
		
		//기존글 가져온다 ( 1. 이미지 교체시 삭제하기 위해  2. 업데이트 대상이 있는지 확인)
		Post post = postMapper.selectPostByUserIdPostId(userId, postId);
		//이용자 한명마다 쓰레드를 사용하는데 단체로 사용하게 되면 한명이 사용하면 다른 사람의 사용이 멈춰지면서 느려지는 사이트가 된다.(절대 사용X)
		System.out.println();
		
		if(post == null) {
			log.warn("[글 수정] post is null. userId:{}, postId:{}",userId, postId);
			return;
		}
		
		//파일이 있으면
		// 1) 새 이미지를 업로드
		// 2) 1번 단계가 성공하면 기존 이미지가 잇을떄 삭제
		String imagePath = null;
		
		if (file != null) {
			//새이미지 업로드
			imagePath = fileManagerService.uploadFile(file, userLoginId);
			
			// 업로드 성공 시(null 아님) 기존이미지가 있으면 제거
			if(imagePath != null && post.getImagePath() != null) {
				//폴더와 이미지 제거(서버에서)
				fileManagerService.deleteFile(post.getImagePath());
			}
		}
		
		// db update
		postMapper.updatePostByPostId(postId, subject, content, imagePath);
		
	}
}
