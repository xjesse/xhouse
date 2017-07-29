package com.netease.course.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestData {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisUserDao dao = context.getBean("myBatisUserDao", MyBatisUserDao.class);
//		System.out.println(dao.getUser(1));
		List<User> userList = dao.getUserList();
		for(User user:userList){
			System.out.println(user.getId()+"\t"+user.getuserName()+"\t"+user.getpassWord());

		}
		
		((ConfigurableApplicationContext)context).close();

	}
	 public static String JM(String inStr) {  
		  char[] a = inStr.toCharArray();  
		  for (int i = 0; i < a.length; i++) {  
		   a[i] = (char) (a[i] ^ 't');  
		  }  
		  String k = new String(a);  
		  return k;  
		 } 
	
	
	 
	 
}
