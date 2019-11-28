package com.clinet.controller;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdminController implements Initializable {

	private static ClientBean cb;

	@FXML
	private TableView<?> tableUsers;

	@FXML
	private Button bUserSettings;

	@FXML
	private Label lRole;

	@FXML
	private Label lFullName;

	@FXML
	private Button bInsert;

	@FXML
	private Button bUpdate;

	@FXML
	private Button bDelete;

	@FXML
	void bUserSettings(ActionEvent event) {
		SettingsController.show();
	}

	public static void show(ClientBean clientBean) {
		cb = clientBean;
		try {
			var stage = new Stage();
			stage.centerOnScreen();
			stage.setScene(new Scene(Load.loadFXML("admin.fxml")));
			stage.setResizable(true);
			stage.setTitle("Расчет таможенных сборов");
			stage.getIcons().add(Load.loadImage("logo.png"));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			lFullName.setText(cb.getLastName() + " " + cb.getFirstName() + " " + cb.getMiddleName());
			lRole.setText(cb.getRole().getName());
			var object = Service.action(Command.GET_USER_TABLE);
			var clientList = new ArrayList<ClientBean>();
			if (object instanceof ArrayList<?>) {
				var al = (ArrayList<?>) object;
				for (Object client : al) {
					if (client instanceof ClientBean) {
						clientList.add((ClientBean) client);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
