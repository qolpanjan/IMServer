package com.example.im.domain;

import java.util.HashMap;
import java.util.List;

import com.example.db.DbHelper;

public class Db {
	public static HashMap<Long, User> map = new HashMap<Long, User>();
	
//	static {
//		for (int i = 0; i < 50; i++) {
//			User user = new User();
//			user.account = 100 + i;
//			user.password = "test";
//			user.nick = "����" + i;
//			user.avatar = 0;
//			map.put(user.account, user);
//		}
//		for (int i = 50; i < 100; i++) {
//			User user = new User();
//			user.account = 100 + i;
//			user.password = "test";
//			user.nick = "����" + i;
//			user.avatar = 0;
//			map.put(user.account, user);
//		}
//	}
	
	/**
	 * 插入用户逻辑
	 * @param user
	 * @return
	 */
	
	public static boolean InsertAccount(User user) {
		DbHelper dbHelper = new DbHelper();
		return dbHelper.insertUser(user);	
	}
	
	/**
	 * 获取通讯录
	 * @param account
	 * @return
	 */
	public static List<Friend> getFriendsList(String account) {
		User user = getAccount(account);
		DbHelper dbHelper = new DbHelper();
		return dbHelper.getFriendsList(user.getId());
		
		
	}
	
	/**
	 * 获取单个用户
	 * @param account
	 * @return
	 */
	
	public static User getAccount(String account){
		DbHelper dbHelper = new DbHelper();
		return dbHelper.getUser(account);
	}
	
	/**
	 * 更新用户信息
	 * @param account
	 * @return
	 */
	public static boolean Update(User account){
		DbHelper dbHelper = new DbHelper();
		return dbHelper.updateUser(account);
	}
	/**
	 * 获取在线用户
	 * @param account
	 * @return
	 */

	public static User getByAccount(String account) {
		if (map.containsKey(account)) {
			return map.get(account);
		}
		return null;

	}

}
