package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface MyBatisTrxODao {

	@Select("select * from trx where id=#{id}")
	public TrxO getTrxO(int id);
//映射关系
	@Results({ @Result(property = "id", column = "id"), 
			@Result(property = "contentId", column = "contentId"),
			@Result(property = "personId", column = "personId"),
			@Result(property = "price", column = "price"),
			@Result(property = "time", column = "time")})
	@Select("select id as id, "
			+ "contentId as contentId, personId as personId ,"
			+ "price as price ,time as time from trx")
	public List<TrxO> getTrxOList();
	
}
