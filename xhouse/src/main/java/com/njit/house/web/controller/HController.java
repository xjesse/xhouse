package com.njit.house.web.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.njit.house.dao.JdbcTemplateDao;
import com.njit.house.service.Client;


@Controller
public class HController {

	private static int jd=0;
	private String speed=null;
	@RequestMapping(value="/house")
	public String house(
			@RequestParam(value="username") String username,
			@RequestParam("housestyle") String housestyle, 
			@RequestParam("tel") String tel, 
			ModelMap map)
			throws IOException {
	
		username= new String(username.getBytes("iso-8859-1"),"utf-8");
		housestyle= new String(housestyle.getBytes("iso-8859-1"),"utf-8");
		System.out.println(username);

		cha(username, housestyle, tel);
		map.addAttribute("username", username);
	return "success";
	}
	
	@RequestMapping(value="/house/show")
	public String houseshow(
			ModelMap map)
			throws IOException {
		jd=getjd();
		if(jd>0){
			speed="已完成：%"+jd;
		}else
			speed="暂时没有数据";
	map.addAttribute("speed",speed );
	return "show";
	}
	
	public static int getjd(){
		int jd1=0;
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		jd1=dao.getjd();
		return jd1;
	}
	
	public static void cha(String username,String housestyle,String tel){
//	System.out.println("ll");
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		dao.insertData(username,housestyle,tel);
		dao.insertHs("0",housestyle);
		((ConfigurableApplicationContext) context).close();
	}
	
	

}
