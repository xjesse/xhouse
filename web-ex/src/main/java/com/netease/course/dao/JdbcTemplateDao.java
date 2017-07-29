package com.netease.course.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class JdbcTemplateDao {
private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insertData(int price,String title,byte[] icon ,String abstract1,byte[] text) {
		this.jdbcTemplate.update("insert into content (price , title ,icon,abstract,text  ) values(?,?,?,?,?)", price, title,icon,abstract1,text);
	}
	public void insertDatas(int price,String title,String abstract1) {
		this.jdbcTemplate.update("insert into content (price , title ,abstract  ) values(?, ?,?)", price, title,abstract1);
	}

	public byte[]  geticon() {
		return this.jdbcTemplate.queryForObject("select icon from content where id=1", byte[].class);
	}
	
	public int  getnum(int contentId ,int personId ) {
		return this.jdbcTemplate.queryForObject("select count(*) from trx where contentId=?&&personId =?",Integer.class,contentId,personId );
	}
	
	public void inserttrx(int contentId,int personId,int price,long time) {
		this.jdbcTemplate.update("insert into trx (contentId  , personId ,price ,time ) values(?,?,?,?)", contentId,personId,price, time);
	}
	
	//插入商品
	public void insertContent(int price ,String title, byte[] icon,String abstract1,byte[] text){
		
		this.jdbcTemplate.update("insert into content (price , title ,icon ,abstract ,text) values(?,?,?,?,?)", price,title,icon, abstract1,text);	
	}
	
	//删除商品
	public void deleteContent(int id ){
		
		this.jdbcTemplate.update("delete from content where id= ?", id);
		this.jdbcTemplate.update(" update content set id=id-1 where id>=? ", id);
	}
	
	//编辑商品
	public void editContent(int price ,String title, byte[] icon,String abstract1,byte[] text,int id ){
		
	this.jdbcTemplate.update(" update content set price=? , title=? ,icon=? ,abstract=? ,text=?where id=? ", price,title,icon, abstract1,text, id);
	}

}
