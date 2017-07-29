package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface MyBatisUserDao {

	@Select("select * from person where id=#{id}")
	public User getUser(int id);
//映射关系
	@Results({ @Result(property = "id", column = "id"), 
			@Result(property = "userName", column = "userName"),
			@Result(property = "passWord", column = "password"),
			@Result(property = "nickName", column = "nickName"),
			@Result(property = "userType", column = "userType")})
	@Select("select id as id, "
			+ "userName as userName, password as passWord ,"
			+ "nickName as nickName ,userType as userType from person")
	public List<User> getUserList();

}
