package com.netease.course.dao;

public class Buycontent {
	private int id; 
	private int contentid;
	private int buyPrice;
	private String title;
	private int buyNum;
	private String image;
	private long time;
	
	public Buycontent(){
		
	}
	
	public int getid(){
		return id;	
	}

	public void setid(int id){
		this.id=id;
	}

	public int getcontentid(){
		return contentid;	
	}

	public void setcontentid(int contentid){
		this.contentid=contentid;
	}
	
	
	public int getbuyPrice(){
		return buyPrice;	
	}

	public void setbuyPrice(int buyPrice){
		this.buyPrice=buyPrice;
	}


	public String gettitle(){
		return title;
		
	}

	public void settitle(String title){
		this.title=title;
		
	}
	
	public String  getimage(){
		return image;
		
	}

	public void setimage(String  image){
		this.image=image;
		
	}

	public int  getbuyNum(){
		return buyNum;
		
	}

	public void setbuyNum(int  buyNum){
		this.buyNum=buyNum;
		
	}
	
	public long gettime(){
		return time;	
	}

	public void settime(long time){
		this.time=time;
	}
	
}
