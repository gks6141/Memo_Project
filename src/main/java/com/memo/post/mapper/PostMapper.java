package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.memo.post.domain.Post;

@Mapper
public interface PostMapper {

	public List<Map<String,Object>> selectPost();

	
	public List<Post> getPostListByUserId(
			@Param("userId")Integer userId,
			@Param("standardId") Integer standardId,
			@Param("direction") String direction,
			@Param("limit") int limit);
	
	//여기서의 리턴 타입은 postId(int)
	public int selectPostIdByUserIdAsSort(
			@Param("userId")int userId,
			@Param("sort") String sort);
	
	public Post selectPostByUserIdPostId(
			@Param("userId") int userid,
			@Param("postId") int postId);
	
	public void insertPostByUserId(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public void updatePostByPostId(
			@Param("postId") int postId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public int deletePostByPostId(
			@Param("userId") int userid,
			@Param("postId") int postId);
}
