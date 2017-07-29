package com.netease.course.service;

import java.security.MessageDigest;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.course.dao.MyBatisUserDao;
import com.netease.course.dao.User;

public class Service {
	Service (){

	}
	public void search (String userName ,String passWord){
		passWord=string2MD5(passWord);
		System.out.println(passWord);
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisUserDao dao = context.getBean("myBatisUserDao", MyBatisUserDao.class);
		List<User> userList = dao.getUserList();
		for(User user:userList){
//			System.out.println(user.getId()+"\t"+user.getuserName()+"\t"+user.getpassWord());
		if (user.getuserName().equals(userName)&&user.getpassWord().equals(passWord)){
			{System.out.println(user.getuserType());
			break;}
		}else
		{System.out.println("0");}
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
	
	    public static String string2MD5(String inStr){  
	        MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	  
	    }
	    
	    
		public static void main(String[] args) {
			Service tool=new Service();
			tool.search("buyer", "reyub");
		}
	 
	
	}
