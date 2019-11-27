module com.project.clinet {
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;
	requires transitive com.bean;

	opens com.clinet to javafx.fxml;
	opens com.clinet.controller to javafx.fxml;

	exports com.clinet;
	exports com.clinet.controller;
}