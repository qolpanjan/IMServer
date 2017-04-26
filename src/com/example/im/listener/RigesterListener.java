package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Db;
import com.example.im.domain.Message;
import com.example.im.domain.MessageType;
import com.example.im.domain.User;

public class RigesterListener extends MessageSender implements OnRecevieMsgListener {
	private MyConnection conn = null;

	public RigesterListener(MyConnection conn) {
		super();
		this.conn = conn;
	}

	public void onReceive(Message fromCient) {
		if (MessageType.MSG_TYPE_REGISTER.equals(fromCient.getType())) {
			try {
				Message toClient = new Message();
				if (MessageType.MSG_TYPE_REGISTER.equals(fromCient.getType())) {
					String[] params = fromCient.getContent().split("#");
					String account = params[0];
					String pwd = params[1];
					String nick = params[2];
					String sex = params[3];
					
					User user = Db.getByAccount(account);
					if (user == null) {
						//用户不存在
						User mUser = new User();
						mUser.setAccount(account);
						mUser.setPassword(pwd);
						mUser.setNick(nick);
						mUser.setSex(sex);
						mUser.setAvatar("192.168.1.102/img/avater_girl.jpg");
						Db.InsertAccount(mUser);
						toClient.setType(MessageType.MSG_TYPE_SUCCESS);
						toClient.setContent("注册成功");;
						toClient(toClient, conn);
					} else {
						// 用户存在
							toClient.setType(MessageType.MSG_TYPE_FAILURE);
							conn.who = user;
							toClient.setType(MessageType.MSG_TYPE_FAILURE); 
							toClient.setContent("已注册");
							toClient(toClient, conn);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}