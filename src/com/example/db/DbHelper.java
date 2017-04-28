package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.im.domain.Friend;
import com.example.im.domain.User;

public class DbHelper {
	
	String Driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/missu";
	String user ="root";
	String password ="123456";
	Connection conn = null;
	
	
	public DbHelper() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	/**
	 * 链接数据驱动
	 * @return 数据库链接对象
	 */
	public Connection getConn(){
		
		try{
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, password);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
//	
//	public static void main(String[] args) {
//		DbHelper db = new DbHelper();
//		User user = db.getUser("alim");
//		System.out.println(user.getNick());
//	}
	/**
	 * 插入新用户到数据库
	 * @param user对象
	 * @return 插入结果 true为插入成功
	 */
	
	
	public boolean insertUser(User user){
		String sql = "INSERT INTO account(id,account,password,nick,avater,sex) value(?,?,?) ";
		boolean result = false;
		PreparedStatement pstmt;
		try{
			pstmt =(PreparedStatement) getConn().prepareStatement(sql);
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNick());
			pstmt.setString(4, user.getAvatar());
			pstmt.setString(5, user.getSex());
			result = pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		//boolean rs=insertFriend(user);
		if (result) {
			return result;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 插入新用户到所有朋友用户
	 * @param user
	 * @return 插入结果，true为成功
	 */
	
	public boolean insertFriend(User user){
		String sql = "INSERT INTO `friend_list`(`account`, `nick`, `avater`, `sex`) VALUES (?,?,?,?)";
		boolean result = false;
		PreparedStatement pstmt;
		try{
			pstmt =(PreparedStatement) getConn().prepareStatement(sql);
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getNick());
			pstmt.setString(3, user.getAvatar());
			pstmt.setString(4, user.getSex());
			result = pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 获取单个用户
	 * @param account
	 * @return 获取到的User对象
	 */
	
	public User getUser(String account){
		 String sql = "select * from account where account = '"+account+"'";    //要执行的SQL
		 //System.out.println(sql);
	
		 User newUser = new User();
		 try{
			 Connection conn = getConn();
			 Statement pstmt = conn.createStatement();
			 ResultSet rs = pstmt.executeQuery(sql);
			 //int col = rs.getMetaData().getColumnCount();	
			 while(rs.next()){
				  newUser.setId(rs.getInt("id"));
				  newUser.setAccount(rs.getString("account"));
				  newUser.setPassword(rs.getString("password"));
				  newUser.setNick(rs.getString("nick"));
				  newUser.setAvatar(rs.getString("avater"));
				  newUser.setSex(rs.getString("sex"));
			
			 }
			
				  
		 }catch(Exception e){
			 System.out.println("getUSER ERROR");
		 }   
          return newUser;
	}
	
	public boolean updateUser(User user) {
		Connection conn = getConn();
	    boolean i = false;
	    String sql = "update account set password='" + user.getPassword()+"',nick = '"+user.getNick()+"',avater = '"+user.getAvatar()+ "',sex = '"+user.getSex()+ "' where account='" + user.getAccount() + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.execute();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	public List<Friend> getFriendsList(int id){
		List<Friend> list = new ArrayList<Friend>();
		String sql = "SELECT * FROM `friend_list` WHERE id = ( SELECT `friends_id` FROM `account_friends` WHERE `account_id`= '"+id+"')";
		Statement stmt ;
		ResultSet rs;
		
		try {
			
			stmt = getConn().createStatement();
			 rs = stmt.executeQuery(sql);
			 
			 while (rs.next()){
				Friend friend = new Friend();
				friend.setId(rs.getInt("id"));
				friend.setAccount(rs.getString("account"));
				friend.setAvatar(rs.getString("avater"));
				friend.setNick(rs.getString("nick"));
				friend.setSex(rs.getString("sex"));
				list.add(friend);
			 }
		rs.close();
		conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
		}
		
	}
	
	
	

