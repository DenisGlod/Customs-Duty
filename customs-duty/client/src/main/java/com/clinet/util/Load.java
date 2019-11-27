package com.clinet.util;

import java.io.IOException;

import com.clinet.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public class Load {

	public static Image loadImage(String imageName) {
		return new Image(App.class.getResourceAsStream("/image/" + imageName));
	}

	public static Parent loadFXML(String fxml) throws IOException {
		var loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml));
		return loader.load();
	}

}
