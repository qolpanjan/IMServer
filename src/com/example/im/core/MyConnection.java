package com.example.im.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.im.domain.MessageBean;
import com.example.im.domain.User;


public class MyConnection extends Thread {
	private Socket scoket = null;
	// readUTF
	public DataOutputStream writer = null;
	public DataInputStream reader = null;
	public User who = null;

	public String ip;
	public int port;
	/**
	 * 构造方法初始化Socket
	 * @param scoket
	 */

	public MyConnection(Socket scoket) {
		super();
		try {
			this.scoket = scoket;
			writer = new DataOutputStream(this.scoket.getOutputStream());
			reader = new DataInputStream(this.scoket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 很久IP地址和端口初始化
	 * @param ip
	 * @param port
	 */
	public MyConnection(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
		init(ip, port);
	}
	

	private void init(String ip, int port) {
		try {
			this.scoket = new Socket(ip, port);
			writer = new DataOutputStream(this.scoket.getOutputStream());
			reader = new DataInputStream(this.scoket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 链接方法
	public void connect() {
		if (this.scoket == null) {
			init(ip, port);//如果没有connection对象，立刻初始化
		}
		flag = true;
		start();
	}

	// 断开链接
	public void disconnect() {
		try {
			flag = false;
			writer.close();
			reader.close();
			// stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------添加监听器------
	// 1.接受监听器接口
	public static interface OnRecevieMsgListener {
		public void onReceive(MessageBean msg);
	}

	// 2.所有的监听器放在一个List里管理
	private List<OnRecevieMsgListener> listeners = new ArrayList<OnRecevieMsgListener>();

	// 3.添加监听器
	public void addOnRecevieMsgListener(OnRecevieMsgListener listener) {
		listeners.add(listener);
	}

	// 4.从集合中删除监听器
	public void removeOnRecevieMsgListener(OnRecevieMsgListener listener) {
		listeners.remove(listener);
	}

	private boolean flag = true;

	@Override
	public void run() {
		super.run();
		//读取客户端发来的消息
		while (flag) {
			try {
				String xml = reader.readUTF();
				System.out.println("收到一个消息---------------------------\n"+xml);
				if (xml != null && !"".equals(xml)) {
					MessageBean msg = new MessageBean();
					
					msg = (MessageBean) msg.fromXML(xml);
					for (OnRecevieMsgListener l : listeners) {
						l.onReceive(msg);
					}
				}
			} catch (EOFException e) {
//				e.printStackTrace();
				System.out.println("=链接不存在---");
				if (who != null) {
					ConnectionManager.remove(who.getAccount());
				}
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("出错了！！！！");
				if (who != null) {
					ConnectionManager.remove(who.getAccount());
				}
				disconnect();
			}
		}

	}
}
