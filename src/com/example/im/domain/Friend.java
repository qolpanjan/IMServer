package com.example.im.domain;

public class Friend extends ProtocalObj {

	    private int id = 0;
	    private String account;// 账号 QQ号
	    private String nick = "";// 昵称
	    private String sex="true";
	    private String avatar="";


	 
	    public Friend(int id, String account, String nick, String sex, String avatar) {
	        this.id = id;
	        this.account = account;
	        this.nick = nick;
	        this.sex = sex;
	        this.avatar = avatar;
	    }

	  
	    public Friend() {
	    }

	    public String getAccount() {
	        return account;
	    }

	    public void setAccount(String account) {
	        this.account = account;
	    }

	    public String getNick() {
	        return nick;
	    }

	    public void setNick(String nick) {
	        this.nick = nick;
	    }

	    public String getAvatar() {
	        return avatar;
	    }

	    public void setAvatar(String avatar) {
	        this.avatar = avatar;
	    }

	    public int getBelongTo() {
	        return id;
	    }

	    public void setBelongTo(String belongTo) {
	        this.id = Integer.getInteger(belongTo);
	    }



	    public String getSex() {
	        return sex;
	    }

	    public void setSex(String sex) {
	        this.sex = sex;
	    }

	    public int getId() {
	        return this.id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }




	}


