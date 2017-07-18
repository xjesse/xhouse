package com.njit.house.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njit.house.dao.JdbcTemplateDao;

public class Client implements Runnable{
	private int jd;

		public void run() {

			ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
			JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
			
			try{
		          ServerSocket serverSocket = new ServerSocket(4000);


		          while(true){
		               System.out.println("Android socket 等待接受客户的连接");
		               Socket client = serverSocket.accept();//接受客户端请求
			          BufferedReader  in=null;
		               try{
	  
  
		                   in = new BufferedReader
		                   (new InputStreamReader(client.getInputStream()));
		            	   System.out.println(in);
		                
		            	    String   str = in.readLine();
//		                   System.out.println("0");
		            	   this.jd = Integer.valueOf(str).intValue();
		                   System.out.println("read:"+str);
		                   dao.updatejd(Integer.valueOf(str).intValue());
//		                   out.close();
		               }catch(Exception e){
		                   System.out.println(e.getMessage());
		                   e.printStackTrace();
		               }
		               finally{
		            	   in.close();
		                   client.close();
		                   System.out.println("Android socket close");
		               }
		                
		           }
		       }catch(Exception e){
		           System.out.println(e.getMessage());
		       }
		}
	
	
	public int getjd(){
		return jd;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
	
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
        Thread desktopServerThread = new Thread(new Client());
        desktopServerThread.start();

	}

}

