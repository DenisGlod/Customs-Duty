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
		try (ObjectOutputStream oos = new ObjectOutputStream(socketClient.getOutputStream()); ObjectInputStream ois = new ObjectInputStream(socketClient.getInputStream())) {
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
			case GET_PRODUCT_TABLE:
				oos.writeObject(Command.GET_PRODUCT_TABLE);
				oos.flush();
				result = ois.readObject();
				break;
			case GET_CARGO_TABLE:
				oos.writeObject(Command.GET_CARGO_TABLE);
				oos.flush();
				result = ois.readObject();
				break;
			case GET_POST_TABLE:
				oos.writeObject(Command.GET_POST_TABLE);
				oos.flush();
				result = ois.readObject();
				break;
			case GET_PRODUCTCARGO_TABLE:
				oos.writeObject(Command.GET_PRODUCTCARGO_TABLE);
				oos.flush();
				result = ois.readObject();
				break;
			case ADD_USER:
				oos.writeObject(Command.ADD_USER);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case UPDATE_USER:
				oos.writeObject(Command.UPDATE_USER);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case DELETE_USER:
				oos.writeObject(Command.DELETE_USER);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case ADD_PRODUCT:
				oos.writeObject(Command.ADD_PRODUCT);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case UPDATE_PRODUCT:
				oos.writeObject(Command.UPDATE_PRODUCT);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case DELETE_PRODUCT:
				oos.writeObject(Command.DELETE_PRODUCT);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case ADD_CARGO:
				oos.writeObject(Command.ADD_CARGO);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case UPDATE_CARGO:
				oos.writeObject(Command.UPDATE_CARGO);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case DELETE_CARGO:
				oos.writeObject(Command.DELETE_CARGO);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case DELETE_PRODUCTCARGO:
				oos.writeObject(Command.DELETE_PRODUCTCARGO);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			case DELETE_POST:
				oos.writeObject(Command.DELETE_POST);
				oos.writeObject(object);
				oos.flush();
				result = ois.readObject();
				break;
			}
		}
		return result;
	}

}
