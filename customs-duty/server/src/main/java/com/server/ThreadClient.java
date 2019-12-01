package com.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.bean.Command;
import com.server.dao.service.Service;

public class ThreadClient implements Runnable {

	private static Socket clientDialog;

	public ThreadClient(Socket client) {
		ThreadClient.clientDialog = client;
	}

	@Override
	public void run() {
		try (ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream()); ObjectInputStream oin = new ObjectInputStream(clientDialog.getInputStream())) {
			System.out.println("----- Получение данных от клиента...");
			System.out.println("clientDialog.hashCode() -> " + clientDialog.hashCode());
			var action = (Command) oin.readObject();
			System.out.println("Принятое действие от клиента -> " + action);
			Object data = null;
			Object responceObject = null;
			switch (action) {
			case GET_USER_TABLE:
			case GET_CARGO_TABLE:
			case GET_POST_TABLE:
			case GET_PRODUCTCARGO_TABLE:
			case GET_PRODUCT_TABLE:
				responceObject = Service.action(action);
				break;
			case LOGIN:
			case UPDATE_USER:
			case DELETE_USER:
			case DELETE_PRODUCT:
			case DELETE_CARGO:
			case DELETE_PRODUCTCARGO:
			case DELETE_POST:
				data = oin.readObject();
				System.out.println("Принятые данные ->" + data);
				responceObject = Service.action(action, data);
				break;
			}
			System.out.println("Отправляемые данные ->" + responceObject);
			System.out.println("clientDialog.hashCode() -> " + clientDialog.hashCode());
			System.out.println("Отправка данных клиенту...");
			oos.writeObject(responceObject);
			System.out.println("----- Отправлено.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
