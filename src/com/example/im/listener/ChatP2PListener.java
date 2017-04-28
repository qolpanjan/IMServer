package com.example.im.listener;

import java.io.IOException;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.MessageBean;
import com.example.im.domain.MessageType;


public class ChatP2PListener extends MessageSender implements OnRecevieMsgListener {
	public void onReceive(MessageBean fromOneClient) {
		if (MessageType.MSG_TYPE_CHAT_P2P.equals(fromOneClient.getType())) {
			MyConnection anotherOne = ConnectionManager.get(fromOneClient.getTo());
			try {
				toClient(fromOneClient, anotherOne);
				System.out.println("发送给另一个客户端");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
