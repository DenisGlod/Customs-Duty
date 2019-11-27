package com.clinet.error;

import com.clinet.util.Load;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlterMessageBox {

	private AlterMessageBox() {
	}

	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ошибка");
		alert.setHeaderText(message);
		var stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Load.loadImage("logo.png"));
		alert.showAndWait();
	}

	public static void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Информация");
		alert.setHeaderText(message);
		var stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Load.loadImage("logo.png"));
		alert.showAndWait();
	}

	public static void showInfo(int message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Информация");
		alert.setHeaderText(String.valueOf(message));
		var stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Load.loadImage("logo.png"));
		alert.showAndWait();
	}

}
