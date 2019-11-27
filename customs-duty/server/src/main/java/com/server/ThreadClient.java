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
		try (ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
				ObjectInputStream oin = new ObjectInputStream(clientDialog.getInputStream())) {
			System.out.println("clientDialog.hashCode() -> " + clientDialog.hashCode());
			System.out.println("clientDialog.isClosed() -> " + clientDialog.isClosed());
			System.out.println("----- Получение данных от клиента...");
			var action = (Command) oin.readObject();
			System.out.println("Принятое действие от клиента -> " + action);
			Object data = null;
			switch (action) {
			case LOGIN:
				data = oin.readObject();
				System.out.println("Данные" + data);
				break;
			case GET_USER_TABLE:
				break;
			}
			var responceObject = Service.action(action, data);
			System.out.println("Отправка данных клиенту...");
			System.out.println(responceObject);
			oos.writeObject(responceObject);
			System.out.println("----- Отправлено.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("clientDialog.hashCode() -> " + clientDialog.hashCode());
		System.out.println("clientDialog.isClosed() -> " + clientDialog.isClosed());
	}

}
