package com.netease.course.dao;



//price bigint  comment "当前价格",
//
//title varchar(100) comment "标题",
//
//icon blob comment "图片",
//
//abstract varchar(200) comment "摘要",
//
//text blob comment "正文"  )

public class Content {
	
private int id; 
private int price;
private String title;
private  byte[] icon;
private String abstract1;
private byte[] text;
private boolean isBuy;
private boolean isSell;
private String image;
private int buyNum;
private  String summary;
private String detail;

public Content(){
	
}
	
public Content(int id,int price,
				String title,byte[] icon,
				String abstract1,byte[] text  ){
	this.id=id;
	this.price=price;
	this.title=title;
	this.icon=icon;
	this.abstract1=abstract1;
	this.text=text;	
	this.isBuy=false;
}
	
public int getid(){
	return id;	
}

public void setid(int id){
	this.id=id;
}

public int getprice(){
	return price;	
}

public void setprice(int price){
	this.price=price;
}


public String gettitle(){
	return title;
	
}

public void settitle(String title){
	this.title=title;
	
}

public byte[] geticon(){
	return icon;
	
}

public void seticro(byte[] icon){
	this.icon=icon;
	
}

public String getabstract1(){
	return abstract1;
	
}

public void  setabstract1(String abstract1){
	this.abstract1=abstract1;
	
}

public byte[] gettext(){
	return text;
	
}

public void settext(byte[] text){
	this.text=text;
	
}

public boolean getisBuy(){
	return isBuy;
	
}

public void setisBuy(boolean isBuy){
	this.isBuy=isBuy;
	
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

public String  getsummary(){
	return summary;
	
}

public void setsummary(String  summary){
	this.summary=summary;
	
}

public String  getdetail(){
	return detail;
	
}

public void setdetail(String  detail){
	this.detail=detail;
	
}


public boolean getisSell(){
	return isSell;
	
}

public void setisSell(boolean isSell){
	this.isSell=isSell;
	
}


}
