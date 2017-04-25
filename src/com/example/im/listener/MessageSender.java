package com.example.im.listener;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.example.im.core.MyConnection;
import com.example.im.core.ConnectionManager;
import com.example.im.domain.Message;

public class MessageSender {
	/**
	 * ��һ���ͻ��˷�����Ϣ����Ե�����
	 * 
	 * @param msg
	 * @param conn
	 * @throws IOException
	 */
	public void toClient(Message msg, MyConnection conn) throws IOException {
		System.out.println("������ǰ�ͻ���to Client \n" + msg.toXml());
		if (conn != null) {
			conn.writer.writeUTF(msg.toXml());
		}
	}

	/**
	 * �����ӽ��������еĿͻ��˷�����Ϣ
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void toEveryClient(Message msg) throws IOException {
		System.out.println("Ⱥ�����пͻ���  to toEveryClient Client \n" + msg.toXml());
		// conn.writer.writeUTF(toClient.toXml());
		Set<Map.Entry<String, MyConnection>> allOnLines = ConnectionManager.conns.entrySet();
		for (Map.Entry<String, MyConnection> entry : allOnLines) {
			entry.getValue().writer.writeUTF(msg.toXml());
		}
	}

	public void toOtherClient(Message msg) throws IOException {
		System.out.println("Ⱥ�����������ͻ���  to toEveryClient Client \n"
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
