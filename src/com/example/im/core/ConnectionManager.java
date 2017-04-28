package com.example.im.core;

import java.util.HashMap;

import com.example.im.domain.FrienList;
import com.example.im.domain.Friend;
import com.example.im.domain.OnlineFriendList;
import com.example.im.domain.User;


public class ConnectionManager {
	public static HashMap<String, MyConnection> conns = new HashMap<String, MyConnection>();
	public static OnlineFriendList list = new OnlineFriendList();
	public static FrienList frienList = new FrienList();
	
	
	

	public static void put(User account, MyConnection conn) {
		System.out.println("====账号" + account + "上线了");
		remove(account.getAccount());
		conns.put(account.getAccount(), conn);
		User u = account;
		Friend item = new Friend();
		item.setAccount(u.getAccount());
		item.setAvatar(u.getAvatar());
		item.setNick(u.getNick());
		list.buddyList.add(item);
	}

	public static void remove(String account) {
		if (conns.containsKey(account)) {
			//System.out.println("====账号" + account + "下线了");
			conns.remove(account);
			Friend delete = null;
			for (Friend item : list.buddyList) {
				if (account == item.getAccount()) {
					delete = item;
					break;
				}
			}
			if (delete != null) {
				System.out.println("====账号下线了" + account);
				list.buddyList.remove(delete);

			}
		}
	}

	public static MyConnection get(String account) {
		if (conns.containsKey(account)) {
			return conns.get(account);
		}
		return null;
	}
}
