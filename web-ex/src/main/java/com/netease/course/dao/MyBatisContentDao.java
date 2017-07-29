package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface MyBatisContentDao {
	@Select("select * from Content where id=#{id}")
	public Content getContent(int id);
//映射关系前java后sql
	@Results({ @Result(property = "id", column = "id"), 
			@Result(property = "price", column = "price"),
			@Result(property = "title", column = "title"),
			@Result(property = "icon", column = "icon"),
			@Result(property = "abstract1", column = "abstract"),
			@Result(property = "text", column = "text")
	
	})
	//映射关系前sql后java
	@Select("select id as id, "
			+ "price as price, title as title ,"
			+ "icon as icon ,abstract as abstract1,"
			+ "text as text from content")
	public List<Content> getContentList();
}
