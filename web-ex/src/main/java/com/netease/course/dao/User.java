package com.netease.course.dao;

public class User {
	private int id;
	private String userName;
	private String passWord;
	private String nickName;
	private int userType;
	private boolean lg;

	public User() {
	}
	
	public User(int id, 
			String userName, 
			String passWord,
			String nickName,
			int  userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.nickName=nickName;
		this.userType=userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getpassWord() {
		return passWord;
	}

	public void setpassWord(String passWord) {
		this.passWord = passWord;
	}

	
	public String getnickName(){
		return nickName;
	}
	
	public void setnickName(String nickName){
		this.nickName=nickName;
	}
	
	
	public int getuserType(){
		return userType;
	}
	
	public void setuserType(int userType){
	this.userType=userType;
	}
	
	public boolean getlg(){
		return lg;
}

	public void setlg(Boolean lg){
		this.lg=lg;
}

}
