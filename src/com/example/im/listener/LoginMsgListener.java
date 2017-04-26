package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Db;
import com.example.im.domain.FrienList;
import com.example.im.domain.Message;
import com.example.im.domain.MessageType;
import com.example.im.domain.User;


public class LoginMsgListener extends MessageSender implements OnRecevieMsgListener {
	private MyConnection conn = null;

	public LoginMsgListener(MyConnection conn) {
		super();
		this.conn = conn;
	}

	public void onReceive(Message fromCient) {
		if (MessageType.MSG_TYPE_LOGIN.equals(fromCient.getType())) {
			try {
				Message toClient = new Message();
				if (MessageType.MSG_TYPE_LOGIN.equals(fromCient.getType())) {
					String[] params = fromCient.getContent().split("#");
					String account = params[0];
					String pwd = params[1];
					User user = Db.getByAccount(account);
					if (user == null) {
						// 如果账号不存在
						toClient.setType(MessageType.MSG_TYPE_FAILURE);
						toClient.setContent("账号不存在");;
						toClient(toClient, conn);
					} else {
						// 如果存在，先判断一下密码
						if (user.getPassword().equals(pwd)) {
							// 如果密码正确
							toClient.setType(MessageType.MSG_TYPE_BUDDY_LIST);
							
							FrienList frienList = new FrienList();
							frienList.friendList.addAll(Db.getFriendsList(account));
							conn.who = user;
							ConnectionManager.put(user.getAccount(), conn);  //添加到在线列表
							//OnlineFriendList list = ConnectionManager.list;
							
							toClient.setContent(frienList.toJson());
							//toEveryClient(toClient);
							toClient(toClient,conn);
						} else {
							toClient.setType(MessageType.MSG_TYPE_FAILURE); 
							toClient.setContent("失败");
							toClient(toClient, conn);
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}