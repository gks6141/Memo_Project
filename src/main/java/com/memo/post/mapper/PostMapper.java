package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.memo.post.domain.Post;

@Mapper
public interface PostMapper {

	public List<Map<String,Object>> selectPost();
	public List<Post> getPostListByUserId(Integer userId);
	public List<Post> getPostByUserId(
			@Param("userId") Integer userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
}
