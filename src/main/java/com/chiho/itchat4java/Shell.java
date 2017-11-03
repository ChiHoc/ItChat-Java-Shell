package com.chiho.itchat4java;

import java.io.File;
import javafx.util.Pair;

public class Shell {

	private static Shell instance;
	private Client client;
	private Server server;

	private Shell() {

	}

	public static Shell getInstance() {
		if ( instance == null ) {
			synchronized (Shell.class) {
				instance = new Shell();
				instance.server = new Server("src/main/resources/pyserver.py");
				instance.client = new Client("127.0.0.1", 8001);
			}
		}
		return instance;
	}

	public boolean run() {
//		return runService() && runClient();
		return runClient();
	}

	private boolean runService() {
		return server.start();
	}

	private boolean runClient() {
		return client.start();
	}

	public boolean login() {
		File picDir = new File("src/main/resources/QR.png");
		return client.sendString(genCmd(CmdTypeEnum.LOGIN, new Pair<>("picDir", picDir.getAbsolutePath())));
	}

	@SafeVarargs
	private final String genCmd( CmdTypeEnum cmdTypeEnum, Pair<String, String>... args ) {
		StringBuilder cmdBuilder = new StringBuilder("{\"Cmd\":\"").append(cmdTypeEnum.getCode()).append("\",\"Args\":{");
		if ( args != null && args.length != 0 ) {
			for ( Pair arg : args ) {
				cmdBuilder.append("\"").append(arg.getKey()).append("\":\"").append(arg.getValue()).append("\",");
			}
			cmdBuilder.deleteCharAt(cmdBuilder.length() - 1);
		}
		cmdBuilder.append("}}");
		return cmdBuilder.toString();
	}
}
