package com.clinet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.bean.ClientBean;
import com.bean.Command;
import com.bean.RoleBean;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditControllerForUserTable implements Initializable {

	private static Object data;
	private static Command command;
	private static Stage stage;

	@FXML
	private TextField tLogin;

	@FXML
	private TextField tPass;

	@FXML
	private ComboBox<String> cRole;

	@FXML
	private ComboBox<String> cSatus;

	@FXML
	private TextField tLastName;

	@FXML
	private TextField tFirstName;

	@FXML
	private TextField tMiddleName;

	@FXML
	void onSaveAction(ActionEvent event) {
		int flag = 0;
		try {
			switch (command) {
			case UPDATE_USER:
				var roleBean = new RoleBean((long) cRole.getSelectionModel().getSelectedIndex() + 1, cRole.getSelectionModel().getSelectedItem());
				boolean status;
				if (cSatus.getSelectionModel().getSelectedIndex() == 0) {
					status = true;
				} else {
					status = false;
				}
				var clientBean = new ClientBean(((ClientBean) data).getId(), tLogin.getText(), tPass.getText(), roleBean, status, tFirstName.getText(), tLastName.getText(), tMiddleName.getText());
				flag = (int) Service.action(Command.UPDATE_USER, clientBean);
				break;
			case ADD_USER:

				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
		if (flag == 1) {
			AlterMessageBox.showInfo("Операция " + command.toString() + " выполнена успешно.");
		} else {
			AlterMessageBox.showError("Операция " + command.toString() + " не выполнена.");
		}
		stage.hide();
	}

	public static void show(Command command) {
		show(command, null);
	}

	public static void show(Command command, Object data) {
		AddEditControllerForUserTable.command = command;
		AddEditControllerForUserTable.data = data;
		try {
			var stage = new Stage();
			stage.centerOnScreen();
			switch (command) {
			case ADD_USER:
				stage.setScene(new Scene(Load.loadFXML("AddEditUser.fxml")));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_USER:
				stage.setScene(new Scene(Load.loadFXML("AddEditUser.fxml")));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			default:
				break;
			}
			stage.setResizable(false);
			stage.showAndWait();
			AddEditControllerForUserTable.stage = stage;
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		switch (command) {
		case UPDATE_USER:
			var clientBean = (ClientBean) data;
			tLogin.setText(clientBean.getLogin());
			tPass.setText(clientBean.getPassword());
			cRole.getItems().addAll(FXCollections.observableArrayList("Администратор", "Пользователь"));
			cRole.setValue(clientBean.getRole().getName());
			cSatus.getItems().addAll(FXCollections.observableArrayList("Активирован", "Деактивирован"));
			if (clientBean.getStatus()) {
				cSatus.setValue("Активирован");
			} else {
				cSatus.setValue("Деактивирован");
			}
			tLastName.setText(clientBean.getLastName());
			tFirstName.setText(clientBean.getFirstName());
			tMiddleName.setText(clientBean.getMiddleName());
			break;
		case ADD_USER:
			cRole.getItems().addAll(FXCollections.observableArrayList("Администратор", "Пользователь"));
			cRole.getSelectionModel().select(1);
			cSatus.getItems().addAll(FXCollections.observableArrayList("Активирован", "Деактивирован"));
			cSatus.getSelectionModel().select(0);
			break;
		default:
			break;
		}
	}

}
