package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Db;
import com.example.im.domain.MessageBean;
import com.example.im.domain.MessageType;
import com.example.im.domain.User;

public class GetUser extends MessageSender implements OnRecevieMsgListener {
	private MyConnection conn = null;

	public GetUser(MyConnection conn) {
		super();
		this.conn = conn;
	}

	public void onReceive(MessageBean fromCient) {
		if (MessageType.MSG_TYPE_GETUSER.equals(fromCient.getType())) {
			try {
				MessageBean toClient = new MessageBean();
				if (MessageType.MSG_TYPE_GETUSER.equals(fromCient.getType())) {
					String account = fromCient.getContent();
					
					User user = Db.getAccount(account);
					if (user== null) {
				
						toClient.setType(MessageType.MSG_TYPE_FAILURE);
						toClient.setContent("数据库错误");;
						toClient(toClient, conn);
					} else {
						// 用户存在
						
							toClient.setType(MessageType.MSG_TYPE_GETUSER_SUCCESS);
							conn.who = user;
							toClient.setContent(user.getAccount()+"#"+user.getPassword()+"#"+user.getNick()+"#"+user.getAvatar()+"#"+user.getSex());
							
							toClient(toClient, conn);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}