package com.netease.course.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.course.dao.Buycontent;
import com.netease.course.dao.Content;
import com.netease.course.dao.JdbcTemplateDao;
import com.netease.course.dao.MyBatisContentDao;
import com.netease.course.dao.MyBatisTrxODao;
import com.netease.course.dao.MyBatisUserDao;
import com.netease.course.dao.TrxO;
import com.netease.course.dao.User;
public class Jdbctool {

	public static void main(String [] args){//测试
		Jdbctool jtl=new Jdbctool();
		System.out.println(jtl.getContentlist().size());
		jtl.delete(4);
		System.out.println(jtl.getContentlist().size());
	}
	
	public List<Content>  getContentlist(){
		Imagetool imt=new Imagetool();
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisContentDao dao = context.getBean("myBatisContentDao", MyBatisContentDao.class);
		List<Content> ContentList = dao.getContentList();
		

		for (Content c1 :ContentList ){
		c1.setimage("image1?id="+c1.getid());			
	}	
		
//		System.out.println(ContentList.size());
		((ConfigurableApplicationContext)context).close();		
		return ContentList;	
	}
	
	public boolean login(String userName,String password){
		boolean mach=false;
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisUserDao dao = context.getBean("myBatisUserDao", MyBatisUserDao.class);

		List<User> userList = dao.getUserList();
		for(User user:userList){
//			System.out.println(user.getId()+"\t"+user.getuserName()+"\t"+user.getpassWord());
		if(user.getuserName().equals(userName)&&user.getpassWord().equals(password)){
			mach=true;
			break;
		}
		}
		((ConfigurableApplicationContext)context).close();
		return mach;
	}
	
	
	
	public User getuser(String userName,String password){
		User user=null;
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisUserDao dao = context.getBean("myBatisUserDao", MyBatisUserDao.class);

		List<User> userList = dao.getUserList();
		for(User user1:userList){
//			System.out.println(user1.getId()+"\t"+user1.getuserName()+"\t"+user1.getpassWord());
		if(user1.getuserName().equals(userName)&&user1.getpassWord().equals(password)){
			user=new User(user1.getId(), 
					user1.getuserName(), 
					user1.getpassWord(),
					user1.getnickName(),
					user1.getuserType());
			
			break;
		}
		}
		((ConfigurableApplicationContext)context).close();
		return user;
	}
	
	public int getnum(int contentId,int personId){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		int num=dao.getnum(contentId, personId);
		
		
		((ConfigurableApplicationContext)context).close();
		return num;
	}
	
	
	
	public void  updatetrx(int contentId,int personId,int price,long time,int num){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		for(int i=0;i<num;i++){
		dao.inserttrx(contentId, personId, price, time);
		}
		((ConfigurableApplicationContext)context).close();
	}
	

	
	
	public  List<TrxO>  getltrxO(){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		MyBatisTrxODao dao = context.getBean("myBatisTrxODao", MyBatisTrxODao.class);

		List<TrxO> TrxOList = dao.getTrxOList();

		((ConfigurableApplicationContext)context).close();		
		return TrxOList;	
	}
		

	public List<Buycontent>  getBuycontentlist(){
		//相同时间合并为同一次交易
		List<Buycontent> BuyontentList = new ArrayList();
//		BuyontentList.add(new Buycontent());
		List<TrxO> TrxOlist=getltrxO();
		int num=1;
		int j=1;
		for(int i=1 ;i<TrxOlist.size();i++){
//			long time= TrxOlist.get(i).gettime();
			if (TrxOlist.get(i-1).gettime()==TrxOlist.get(i).gettime()&&//与上次记录的时间相同
			    TrxOlist.get(i-1).getcontentId()==TrxOlist.get(i-1).getcontentId()//与上个记录的内容id相同
			    ){
				num++;
			}
			else{
				Buycontent c1=new Buycontent();
				c1.setid(j);
				c1.setcontentid(TrxOlist.get(i-1).getcontentId());
				c1.setbuyNum(num);
				c1.settime(TrxOlist.get(i-1).gettime());
				c1.setbuyPrice(TrxOlist.get(i-1).getprice());
				BuyontentList.add(c1);
				j++;
				num=1;
	
			}
	
		}
		
		Buycontent c1=new Buycontent();
		c1.setid(j);
		c1.setcontentid(TrxOlist.get(TrxOlist.size()-1).getcontentId());
		c1.setbuyNum(num);
		c1.settime(TrxOlist.get(TrxOlist.size()-1).gettime());
		c1.setbuyPrice(TrxOlist.get(TrxOlist.size()-1).getprice());
		BuyontentList.add(c1);
		
		return BuyontentList;	
	}
	
	
	//插入数据库
	public void  insert(Content c1){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		byte[] text=new byte[1024];
		text=c1.getdetail().getBytes();
		dao.insertContent(c1.getprice(), c1.gettitle(), c1.geticon(), c1.getabstract1(), text);
 	    ((ConfigurableApplicationContext)context).close();
	}
	//删除商品
	public void  delete(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		dao.deleteContent(id);		
 	    ((ConfigurableApplicationContext)context).close();
	}
	
	public void  edit(Content c1){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao", JdbcTemplateDao.class);
		byte[] text=new byte[1024];
		text=c1.getdetail().getBytes();
		dao.editContent(c1.getprice(), c1.gettitle(), c1.geticon(), c1.getabstract1(), text,c1.getid());

 	    ((ConfigurableApplicationContext)context).close();
	}
	
	
}
