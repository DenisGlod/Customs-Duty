package com.clinet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.Command;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.RoleBean;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddEditController {

	private Object data;
	private Command command;
	private Stage stage;
	public int status = 0;

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
	private TextField tCodeProduct;
	@FXML
	private TextField tNameProduct;

	@FXML
	private TextField tUuid;
	@FXML
	private DatePicker dDate;
	@FXML
	private ComboBox<PostBean> cPost;

	@FXML
	private void onSaveAction(ActionEvent event) {
		int flag = 0;
		boolean status;
		try {
			switch (command) {
			case UPDATE_USER:
				if (cSatus.getSelectionModel().getSelectedIndex() == 0) {
					status = true;
				} else {
					status = false;
				}
				var clientBeanUpdate = new ClientBean(((ClientBean) data).getId(), tLogin.getText(), tPass.getText(),
						new RoleBean((long) cRole.getSelectionModel().getSelectedIndex() + 1, cRole.getSelectionModel().getSelectedItem()), status, tFirstName.getText(), tLastName.getText(),
						tMiddleName.getText());
				flag = (int) Service.action(Command.UPDATE_USER, clientBeanUpdate);
				break;
			case ADD_USER:
				if (cSatus.getSelectionModel().getSelectedIndex() == 0) {
					status = true;
				} else {
					status = false;
				}
				var clientBeanAdd = new ClientBean(tLogin.getText(), tPass.getText(),
						new RoleBean((long) cRole.getSelectionModel().getSelectedIndex() + 1, cRole.getSelectionModel().getSelectedItem()), status, tFirstName.getText(), tLastName.getText(),
						tMiddleName.getText());
				flag = (int) Service.action(Command.ADD_USER, clientBeanAdd);
				break;
			case UPDATE_PRODUCT:
				var dataProductBean = (ProductBean) data;
				var productBeanUpdate = new ProductBean(dataProductBean.getId(), Integer.parseInt(tCodeProduct.getText()), tNameProduct.getText());
				flag = (int) Service.action(Command.UPDATE_PRODUCT, productBeanUpdate);
				break;
			case ADD_PRODUCT:
				var productBeanAdd = new ProductBean(Integer.parseInt(tCodeProduct.getText()), tNameProduct.getText());
				flag = (int) Service.action(Command.ADD_PRODUCT, productBeanAdd);
				break;
			case UPDATE_CARGO:
				var cargoBean = (CargoBean) data;
				var updateCargoBean = new CargoBean(cargoBean.getId(), tUuid.getText(), dDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyy")), cPost.getValue());
				flag = (int) Service.action(Command.UPDATE_CARGO, updateCargoBean);
				break;
			case ADD_CARGO:
				var addCargoBean = new CargoBean(tUuid.getText(), dDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyy")), cPost.getValue());
				flag = (int) Service.action(Command.ADD_CARGO, addCargoBean);
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
		this.status = -1;
		stage.close();
	}

	public void show(Command command, Object data, Stage stage) {
		try {
			this.data = data;
			this.command = command;
			this.stage = stage;
			status = 1;
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
			case UPDATE_PRODUCT:
				var productBean = (ProductBean) data;
				tCodeProduct.setText(Integer.toString(productBean.getCode()));
				tNameProduct.setText(productBean.getName());
				break;
			case ADD_CARGO:
			case UPDATE_CARGO:
				initAddEditCargoComponent();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initAddEditCargoComponent() throws Exception {
		var cargoBean = (CargoBean) data;
		dDate.setValue(LocalDate.now());
		var addPostObject = Service.action(Command.GET_POST_TABLE);
		var listPostBean = Load.createList(addPostObject, PostBean.class);
		cPost.setItems(FXCollections.observableArrayList(listPostBean));
		if (command == Command.UPDATE_CARGO) {
			cPost.getSelectionModel().select(cargoBean.getPost());
			dDate.setValue(LocalDate.parse(cargoBean.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			tUuid.setText(cargoBean.getUuid());
		} else {
			cPost.getSelectionModel().select(0);
		}
		cPost.setConverter(new StringConverter<PostBean>() {
			@Override
			public String toString(PostBean object) {
				if (object != null) {
					return object.getName();
				}
				return null;
			}

			@Override
			public PostBean fromString(String string) {
				return null;
			}
		});
	}

}
