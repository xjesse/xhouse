package com.netease.course.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.course.dao.JdbcTemplateDao;
import com.netease.course.dao.MyBatisUserDao;
import com.netease.course.dao.User;

//将图片转换为btye[]
//将byte转换为图片
public class Imagetool {

	public static void main(String[] args) {
		Imagetool imt=new Imagetool();
		byte [] icon ;
		byte [] text ;
		String mm;
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		byte[] a=dao.geticon();
//		System.out.println(a);
		imt.byte2image(a,"./src/main/webapp/image/1.jpg");

		((ConfigurableApplicationContext)context).close();
	}
	
	
	public void tt(){
		Imagetool imt=new Imagetool();
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		byte[] a=dao.geticon();
		System.out.println(a);
		imt.byte2image(a,"../1.jpg");

		((ConfigurableApplicationContext)context).close();	
	
	}
	
	public  byte[] image2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }

	  
	  
	//byte数组到图片
	  public  void byte2image(byte[] data,String path){
	    if(data.length<3||path.equals("")) return;
	    try{
	    FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	    imageOutput.write(data, 0, data.length);
	    imageOutput.close();
	    System.out.println("Make Picture success,Please find image in " + path);
	    } catch(Exception ex) {
	      System.out.println("Exception: " + ex);
	      ex.printStackTrace();
	    }
	  }
	  //byte数组到16进制字符串
	  public String byte2string(byte[] data){
	    if(data==null||data.length<=1) return "0x";
	    if(data.length>200000) return "0x";
	    StringBuffer sb = new StringBuffer();
	    int buf[] = new int[data.length];
	    //byte数组转化成十进制
	    for(int k=0;k<data.length;k++){
	      buf[k] = data[k]<0?(data[k]+256):(data[k]);
	    }
	    //十进制转化成十六进制
	    for(int k=0;k<buf.length;k++){
	      if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
	      else sb.append(Integer.toHexString(buf[k]));
	    }
	    return "0x"+sb.toString().toUpperCase();
	  }
	  
	  
	  /** 
	     * <把字符串转换成字节数组然后在封装成字符串> 
	     * <功能详细描述> 
	     * @param chinese 
	     * @return 
	     * @see [类、类#方法、类#成员] 
	     */  
	    public static byte[] chineseToString(String chinese)  
	    {  
	            byte[] b = chinese.getBytes();  
	            return b;  
	        }  
	     
	      
	    /** 
	     * <把字节数组封装成的字符串转换成原来的字符串> 
	     * <功能详细描述> 
	     * @param stc 
	     * @return 
	     * @see [类、类#方法、类#成员] 
	     */  
	    public  String stringToChinese(String stc)  
	    {  
	        // 如果传递的字符串为空则直接返回空  
	       
	            // 分割字符串  
	            String[] s = stc.split("@");  
	            if (s.length > 0)  
	            {  
	                // 循环构造BYTE数组  
	                byte[] b = new byte[s.length];  
	                for (int i = 0; i < s.length; i++)  
	                {  
	                    b[i] = (byte)Integer.parseInt(s[i]);  
	                }  
	                  
	                // 根据BYTE数组构造字符串  
	                return new String(b);  
	            }  
	            else  
	            {  
	                return "";  
	            }  
	        }  
	    
	  
	  
/** 
 * 将低字节数组转换为int 
 * @param b byte[] 
 * @return int 
 */  
public  int lBytesToInt(byte[] b)  
{  
    int s = 0;  
    for (int i = 0; i < 3; i++)  
    {  
        if (b[3 - i] >= 0)  
        {  
            s = s + b[3 - i];  
        }  
        else  
        {  
            s = s + 256 + b[3 - i];  
        }  
        s = s * 256;  
    }  
    if (b[0] >= 0)  
    {  
        s = s + b[0];  
    }  
    else  
    {  
        s = s + 256 + b[0];  
    }  
    return s;  
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
