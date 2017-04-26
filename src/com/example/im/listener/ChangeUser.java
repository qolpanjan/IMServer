package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Db;
import com.example.im.domain.Message;
import com.example.im.domain.MessageType;
import com.example.im.domain.User;

public class ChangeUser extends MessageSender implements OnRecevieMsgListener {
	private MyConnection conn = null;

	public ChangeUser(MyConnection conn) {
		super();
		this.conn = conn;
	}

	public void onReceive(Message fromCient) {
		if (MessageType.MSG_TYPE_CHANGE_USER.equals(fromCient.getType())) {
			try {
				Message toClient = new Message();
				if (MessageType.MSG_TYPE_CHANGE_USER.equals(fromCient.getType())) {
					String[] params = fromCient.getContent().split("#");
					//int id = Integer.valueOf(params[0]);
					String account = params[0];
					String pwd = params[1];
					String nick = params[2];
					String avater = params[3];
					String sex = params[4];
					
		
						// 如果用户不存在
						User mUser = new User();
						mUser.setAccount(account);
						mUser.setPassword(pwd);
						mUser.setNick(nick);
						mUser.setSex(sex);
						mUser.setAvatar(avater);
						
						Db.InsertAccount(mUser);
						toClient.setType(MessageType.MSG_TYPE_SUCCESS);
						toClient.setContent("修改成功");;
						toClient(toClient, conn);
				
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}