package com.example.im.core;

import java.util.HashMap;

import com.example.im.domain.Db;
import com.example.im.domain.Friend;
import com.example.im.domain.FriendList;
import com.example.im.domain.User;


public class ConnectionManager {
	public static HashMap<String, MyConnection> conns = new HashMap<String, MyConnection>();
	public static FriendList list = new FriendList();

	public static void put(String account, MyConnection conn) {
		System.out.println("====�˺�" + account + "������");
		remove(account);
		conns.put(account, conn);
		User u = Db.getByAccount(account);
		Friend item = new Friend();
		item.setAccount(u.getAccount());
		item.setAvatar(u.getAvatar());
		item.setNick(u.getNick());
		list.buddyList.add(item);
	}

	public static void remove(String account) {
		if (conns.containsKey(account)) {
//			System.out.println("====�˺�" + account + "������");
			conns.remove(account);
			Friend delete = null;
			for (Friend item : list.buddyList) {
				if (account == item.getAccount()) {
					delete = item;
					break;
				}
			}
			if (delete != null) {
//				System.out.println("====�������������Ƴ� 0000" + account);
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
