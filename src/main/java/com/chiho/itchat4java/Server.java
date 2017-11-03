package com.chiho.itchat4java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {

	private String scriptPath = "src/main/resources/pyserver.py";
	private Process pyProcess;
	private BufferedReader inputReader;

	public Server( String scriptPath ) {
		this.scriptPath = scriptPath;
	}

	public boolean start() {
		try {
			File file = new File(scriptPath);
			ProcessBuilder processBuilder = new ProcessBuilder("python", file.getAbsolutePath());
			pyProcess = processBuilder.start();
			inputReader = new BufferedReader(new InputStreamReader(pyProcess.getInputStream()));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(pyProcess.getErrorStream()));
			String line;
			if ( errorReader.ready() ) {
				while ( ( line = errorReader.readLine() ) != null ) {
					System.out.println(line);
				}
			}
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
			String line;
			try {
				if ( inputReader.ready() ) {
					while ( ( line = inputReader.readLine() ) != null ) {
						System.out.println(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
