package com.example.im.domain;

import java.util.HashMap;

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

	public static User getByAccount(String account) {
		if (map.containsKey(account)) {
			return map.get(account);
		}
		return null;

	}

}
