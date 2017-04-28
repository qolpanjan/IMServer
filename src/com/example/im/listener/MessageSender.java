package com.example.im.listener;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.domain.MessageBean;

public class MessageSender {
	/**
	 * 发送给单个用户
	 * 
	 * @param msg
	 * @param conn
	 * @throws IOException
	 */
	public void toClient(MessageBean msg, MyConnection conn) throws IOException {
		System.out.println("向客户发送消息：\n" + msg.toXml());
		if (conn != null) {
			conn.writer.writeUTF(msg.toXml());
		}
	}

	/**
	 * 发送给所有用户
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void toEveryClient(MessageBean msg) throws IOException {
		System.out.println(" to toEveryClient Client \n" + msg.toXml());
		// conn.writer.writeUTF(toClient.toXml());
		Set<Map.Entry<String, MyConnection>> allOnLines = ConnectionManager.conns.entrySet();
		for (Map.Entry<String, MyConnection> entry : allOnLines) {
			entry.getValue().writer.writeUTF(msg.toXml());
		}
	}

	public void toOtherClient(MessageBean msg) throws IOException {
		System.out.println(" to toEveryClient Client \n"
				+ msg.toXml());
		// conn.writer.writeUTF(toClient.toXml());
		Set<Map.Entry<String, MyConnection>> allOnLines = ConnectionManager.conns.entrySet();
		for (Map.Entry<String, MyConnection> entry : allOnLines) {
			if (!(entry.getValue().who.getAccount().equals(msg.getFrom()))) {
				entry.getValue().writer.writeUTF(msg.toXml());
			}
		}
	}
}
