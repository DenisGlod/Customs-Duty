package com.clinet;

import java.io.IOException;

import com.clinet.controller.LoginController;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		LoginController.show();
	}

	public static void main(String[] args) {
		launch();
	}

}