package com.chiho.itchat4java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Server {

	private String scriptPath = "src/main/resources/pyserver.py";
	private Process pyProcess;

	public Server( String scriptPath ) {
		this.scriptPath = scriptPath;
	}

	public boolean start() {
		try {
			File file = new File(scriptPath);
			ProcessBuilder processBuilder = new ProcessBuilder("python", file.getAbsolutePath());
			pyProcess = processBuilder.start();
			new Thread(new ServerOutputWatchDog()).start();
			// Destory python server before exit
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					super.run();
					if ( pyProcess != null && pyProcess.isAlive() ) {
						pyProcess.destroy();
					}
				}
			});
			Thread.sleep(1000);
		} catch (IOException|InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 接收消息
	 */
	class ServerOutputWatchDog implements Runnable {

		public void run() {
			while ( pyProcess.isAlive() ) {
				try {
					InputStream in = pyProcess.getInputStream();
					if ( in.available() > 0 ) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
						String string;
						while ( ( string = bufferedReader.readLine() ) != null ) {
							System.out.println(string);
						}
					} else {
						BufferedReader errorReader = new BufferedReader(new InputStreamReader(pyProcess.getErrorStream()));
						String string;
						if ( errorReader.ready() ) {
							while ( ( string = errorReader.readLine() ) != null ) {
								System.out.println(string);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
