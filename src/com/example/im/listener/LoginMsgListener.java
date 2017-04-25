package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Db;
import com.example.im.domain.FriendList;
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
						// ������
						toClient.setType(MessageType.MSG_TYPE_FAILURE);
						toClient.setContent("登录成功");;
						toClient(toClient, conn);
					} else {
						// ����
						if (user.getPassword().equals(pwd)) {
							// ��¼ �ɹ�
							toClient.setType(MessageType.MSG_TYPE_BUDDY_LIST);
							// ������������
							// ��������ݵ����Ӷ���
							conn.who = user;
							ConnectionManager.put(user.getAccount(), conn);
							FriendList list = ConnectionManager.list;
							toClient.setContent(list.toJson());
							toEveryClient(toClient);
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