package com.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static ExecutorService executorService = Executors.newFixedThreadPool(15);

	public static void main(String[] args) {
		System.out.println("====== Сервер ====== ");
		System.out.print("Введите номер порта >> ");
		try (ServerSocket serverSocket = new ServerSocket(new Scanner(System.in).nextInt())) {
			System.out.println("-> Сервер запущен <-");
			while (!serverSocket.isClosed()) {
				Socket client = serverSocket.accept();
				executorService.execute(new ThreadClient(client));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}

}
