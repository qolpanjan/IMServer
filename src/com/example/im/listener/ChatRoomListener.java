package com.example.im.listener;

import java.io.IOException;

import com.example.im.core.MyConnection.OnRecevieMsgListener;
import com.example.im.domain.Message;
import com.example.im.domain.MessageType;


public class ChatRoomListener extends MessageSender implements OnRecevieMsgListener {
	public void onReceive(Message fromOneClient) {
		if (MessageType.MSG_TYPE_CHAT_ROOM.equals(fromOneClient.getType())) {
			try {
				toOtherClient(fromOneClient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
