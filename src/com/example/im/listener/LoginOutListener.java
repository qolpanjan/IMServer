package com.example.im.listener;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Message;
import com.example.im.domain.MessageType;


public class LoginOutListener extends MessageSender implements OnRecevieMsgListener {
	public LoginOutListener() {
		super();
	}
	public void onReceive(Message fromCient) {
		if (MessageType.MSG_TYPE_LOGIN_OUT.equals(fromCient.getType())) {
			try {
				Message toClient = new Message();
				toClient.setType(MessageType.MSG_TYPE_SUCCESS);
				toClient.setContent("下线");
				MyConnection conn = ConnectionManager.get(fromCient.getFrom() );
				ConnectionManager.remove(fromCient.getFrom() );
				toClient(toClient, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}