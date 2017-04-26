package com.example.im.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.example.im.core.MyConnection;
import com.example.im.listener.ChatP2PListener;
import com.example.im.listener.ChatRoomListener;
import com.example.im.listener.LoginMsgListener;
import com.example.im.listener.LoginOutListener;
import com.example.im.listener.RigesterListener;


public class MainServer {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//获取Scoket对象实例
			@SuppressWarnings("resource")
			final ServerSocket server = new ServerSocket(8090);
			System.out.println("-------服务器启动成功！-------" + new Date().toString());
			new Thread() {//
				public void run() {
					while (true) {
						MyConnection conn = null;
						try {
							Socket client = server.accept();
							System.out.println("---客户端链接成功---" + client);
							//对各个方法进行监听
							conn = new MyConnection(client);
							conn.addOnRecevieMsgListener(new RigesterListener(conn));
							conn.addOnRecevieMsgListener(new LoginMsgListener(conn));
							conn.addOnRecevieMsgListener(new ChatP2PListener());
							conn.addOnRecevieMsgListener(new ChatRoomListener());
							conn.addOnRecevieMsgListener(new LoginOutListener());
							// 
							conn.connect();
							// 
						} catch (IOException e) {
							e.printStackTrace();
							conn.disconnect();
						}
					}
				};
			}.start();

		} catch (Exception e) {//
			e.printStackTrace();
		}
	}

}
