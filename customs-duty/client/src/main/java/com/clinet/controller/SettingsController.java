package com.clinet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
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

	}

	public static void show() {
		try {
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

	}

}
