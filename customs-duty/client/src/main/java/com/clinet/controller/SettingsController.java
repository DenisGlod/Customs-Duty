package com.clinet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.bean.ClientBean;
import com.bean.Command;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

	private static ClientBean cb;

	@FXML
	private TextField tLastName;

	@FXML
	private TextField tFirstName;

	@FXML
	private TextField tMiddleName;

	@FXML
	private TextField tPassword;

	@FXML
	void bSaveAction(ActionEvent event) {
		try {
			cb.setLastName(tLastName.getText());
			cb.setFirstName(tFirstName.getText());
			cb.setMiddleName(tMiddleName.getText());
			cb.setPassword(tPassword.getText());
			int status = (int) Service.action(Command.UPDATE_USER, cb);
			AlterMessageBox.showInfo("Обновление -> " + status);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void show(ClientBean cb) {
		try {
			SettingsController.cb = cb;
			var stage = new Stage();
			stage.centerOnScreen();
			stage.setScene(new Scene(Load.loadFXML("settings.fxml")));
			stage.setResizable(false);
			stage.setTitle("Настройки");
			stage.getIcons().add(Load.loadImage("settings.png"));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tLastName.setText(cb.getLastName());
		tFirstName.setText(cb.getFirstName());
		tMiddleName.setText(cb.getMiddleName());
		tPassword.setText(cb.getPassword());
	}

}
