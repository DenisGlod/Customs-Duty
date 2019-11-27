package com.clinet.connector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.bean.Command;

public class Service {

	private static String host;
	private static Integer port;

	private static Socket socketClient;

	private Service() {
		throw new UnsupportedOperationException("You cannot create an instance of this class!");
	}

	public static void connect(String host, String port) throws Exception {
		Service.host = host;
		Service.port = Integer.parseInt(port);
		socketClient = new Socket(Service.host, Service.port);
	}

	public static Object action(Command command) throws Exception {
		return action(command, null);
	}

	public static Object action(Command command, Object object) throws Exception {
		Object result = null;
		if (socketClient == null || socketClient.isClosed()) {
			socketClient = new Socket(host, port);
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(socketClient.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socketClient.getInputStream())) {
			switch (command) {
			case LOGIN:
				oos.writeObject(Command.LOGIN);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case GET_USER_TABLE:
				oos.writeObject(Command.GET_USER_TABLE);
				oos.flush();
				result = ois.readObject();
				break;
			default:
				throw new UnsupportedOperationException("Ошибка! Некорректна операция!");
			}
		}
		return result;
	}

}
