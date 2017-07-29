package com.netease.course.dao;

//每条交易的对象
public class TrxO {
private int id; 
private int contentId; 
private int personId;
private int price;
private long time;
	
	public TrxO(){	
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setcontentId(int contentId){
		this.contentId=contentId;
	}
	
	public int getcontentId(){
		return this.contentId;
	}
	
	public void setpersonId(int personId){
		this.personId=personId;
	}
	
	public int getpersonId(){
		return this.personId;
	}
	
	public void setprice(int price){
		this.price=price;
	}
	
	public int getprice(){
		return this.price;
	}
	
	public void settime(long time){
		this.time=time;
	}
	
	public long gettime(){
		return this.time;
	}
	
}
