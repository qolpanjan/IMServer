package com.example.im.domain;

public class User extends ProtocalObj {
	
	private int id ;
	private String account;// �˺� QQ��
	private String password = "";// ����
	private String nick = "";// �ǳ�
	private String avatar;// ͷ��
	private String sex;
	
	
	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getNick() {
		return nick;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getSex() {
		return sex;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	User(int id ,String account,String password,String nick,String avater,String sex){
		this.id = id;
		this.account = account;
		this.password = password;
		this.nick = nick;
		this.avatar = avater;
		this.sex = sex;
	}
	
	

}
