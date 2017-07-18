package com.njit.house.dao;

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

	public void insertData(String username,String housestyle,String tel) {
		this.jdbcTemplate.update("insert into userdata (username, housestyle,tel ) values(?, ?,?)", username, housestyle,tel);
	}
	
	public void insertHs(String jd, String housestyle) {
		this.jdbcTemplate.update("update jdshow set jd=? where id=1", jd);
		this.jdbcTemplate.update("update jdshow set hs=? where id=1", housestyle);
	}
	
	public void updatejd(int jd) {
		this.jdbcTemplate.update("update jdshow set jd=? where id=1", jd);
	}
	public int  getjd() {
		return this.jdbcTemplate.queryForObject("select jd from jdshow where id=1", Integer.class);
	}
	
}
