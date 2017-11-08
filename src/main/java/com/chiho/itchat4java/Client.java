package com.chiho.itchat4java;

import com.chiho.itchat4java.interfaces.Callback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;


public class Client {

	private String serverIp;
	private int port;
	private Socket socket;
	private boolean isConnected = false;
	private BufferedReader bufferedReader;
	private Callback<String> receiveCallback;

	private long lastSendTime; //最后一次发送数据的时间

	public Client( String serverIp, int port ) {
		this.serverIp = serverIp;
		this.port = port;
	}

	/**
	 * 开始连接socket
	 *
	 * @return 布尔值
	 */
	public boolean start( Callback<String> receiveCallback ) {
		this.receiveCallback = receiveCallback;
		return Client.this.connect();
	}

	/**
	 * 停止连接socket
	 *
	 * @return 布尔值
	 */
	public boolean stop() {
		try {
			socket.close();
			isConnected = false;
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 连接
	 *
	 * @return 布尔值
	 */
	private boolean connect() {
		if ( socket != null && !socket.isClosed() ) {
			Client.this.stop();
		}
		socket = new Socket();
		System.out.println("Client - Begin to connect");
		System.out.println("Socket has been closed!");
		try {
			socket.connect(new InetSocketAddress(serverIp, port), 3000);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		isConnected = true;

		lastSendTime = System.currentTimeMillis();
		new Thread(new KeepAliveWatchDog()).start();  //保持长连接的线程，每隔2秒项服务器发一个一个保持连接的心跳消息
		new Thread(new ReceiveWatchDog()).start();    //接受消息的线程，处理消息
		return true;
	}


	/**
	 * 发送字符串
	 *
	 * @param string 字符串
	 *
	 * @return 布尔值
	 */
	public boolean sendString( final String string ) {
		if ( socket == null || socket.isClosed() ) {
			return false;
		}
		OutputStream os;//字节输出流
		try {
			os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
			System.out.println("The sending data is: " + string);
			pw.write(string + "\r\n");
			pw.flush();

		} catch (IOException e) {
			System.out.println("Error sending data!");
			e.printStackTrace();
			Client.this.stop();
			return false;
		}

		return true;
	}

	/**
	 * 发送心跳包
	 */
	class KeepAliveWatchDog implements Runnable {

		long checkDelay = 500;
		long keepAliveDelay = 5000;

		public void run() {
			while ( isConnected ) {
				if ( System.currentTimeMillis() - lastSendTime > keepAliveDelay ) {
					Client.this.sendString("KeepAlive_" + new Date().getTime());
					lastSendTime = System.currentTimeMillis();
				} else {
					try {
						Thread.sleep(checkDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Client.this.stop();
					}
				}
			}
		}
	}

	/**
	 * 接收消息
	 */
	class ReceiveWatchDog implements Runnable {

		public void run() {
			while ( !socket.isClosed() ) {
				try {
					InputStream in = socket.getInputStream();
					if ( in.available() > 0 ) {
						bufferedReader = new BufferedReader(new InputStreamReader(in));
						String string;
						while ( ( string = bufferedReader.readLine() ) != null ) {
							System.out.println("The receiving data is: " + string);
							receiveCallback.call(string);
						}
					} else {
						try {
							socket.sendUrgentData(0xFF);
						} catch (Exception e1) {
							while ( !Client.this.connect() ) {
								Thread.sleep(3000);
							}
						}
						Thread.sleep(10);
					}
				} catch (Exception e) {
					System.out.println("Error receiving data!");
					e.printStackTrace();
					Client.this.stop();
				}
			}
		}
	}
}