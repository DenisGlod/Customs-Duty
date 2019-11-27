package com.clinet.controller;

import java.io.IOException;

import com.bean.ClientBean;
import com.bean.Command;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	private static Stage stage;

	@FXML
	private TextField tLogin;

	@FXML
	private PasswordField tPassword;

	@FXML
	private TextField tHost;

	@FXML
	private TextField tPort;

	@FXML
	void bLoginAction(ActionEvent event) {
		try {
			var host = tHost.getText();
			var port = tPort.getText();
			var login = tLogin.getText();
			var pass = tPassword.getText();
			if (!host.isBlank() && !port.isBlank() && !login.isBlank() && !pass.isBlank()) {
				var client = new ClientBean();
				client.setLogin(login);
				client.setPassword(pass);
				Service.connect(host, port);
				client = (ClientBean) Service.action(Command.LOGIN, client);
				if (client.isEmpty()) {
					AlterMessageBox.showError("Неверный логин или пароль!");
				} else {
					AdminController.show(client);
					stage.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	public static void show() throws IOException {
		var stage = new Stage();
		stage.centerOnScreen();
		stage.setScene(new Scene(Load.loadFXML("login.fxml")));
		stage.setResizable(false);
		stage.setTitle("Расчет таможенных сборов");
		stage.getIcons().add(Load.loadImage("logo.png"));
		stage.show();
		LoginController.stage = stage;
	}

}