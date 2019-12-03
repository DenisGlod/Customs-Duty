package com.clinet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.Command;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.ProductCargoBean;
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

	// Form Client
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

	// Form Product
	@FXML
	private TextField tCodeProduct;
	@FXML
	private TextField tNameProduct;

	// Form Cargo
	@FXML
	private TextField tUuid;
	@FXML
	private DatePicker dDate;
	@FXML
	private ComboBox<PostBean> cPost;

	// Form Post
	@FXML
	private TextField tNamePost;
	@FXML
	private TextField tAddress;

	// From ProductCagro
	@FXML
	private ComboBox<ProductBean> cProduct;
	@FXML
	private ComboBox<CargoBean> cCargo;
	@FXML
	private TextField tWeight;
	@FXML
	private TextField tCustomsDuty;

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
			case UPDATE_PRODUCTCARGO:
				flag = (int) Service.action(Command.UPDATE_PRODUCTCARGO, new ProductCargoBean(((ProductCargoBean) data).getId(), cProduct.getSelectionModel().getSelectedItem(),
						cCargo.getSelectionModel().getSelectedItem(), Double.valueOf(tWeight.getText()), Double.valueOf(tCustomsDuty.getText())));
				break;
			case ADD_PRODUCTCARGO:
				flag = (int) Service.action(Command.ADD_PRODUCTCARGO, new ProductCargoBean(cProduct.getSelectionModel().getSelectedItem(), cCargo.getSelectionModel().getSelectedItem(),
						Double.valueOf(tWeight.getText()), Double.valueOf(tCustomsDuty.getText())));
				break;
			case UPDATE_POST:
				flag = (int) Service.action(Command.UPDATE_POST, new PostBean(((PostBean) data).getId(), tNamePost.getText(), tAddress.getText()));
				break;
			case ADD_POST:
				flag = (int) Service.action(Command.ADD_POST, new PostBean(tNamePost.getText(), tAddress.getText()));
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
			case ADD_PRODUCTCARGO:
			case UPDATE_PRODUCTCARGO:
				initAddEditProductCargoComponent();
				break;
			case UPDATE_POST:
				var postBean = (PostBean) data;
				tNamePost.setText(postBean.getName());
				tAddress.setText(postBean.getAdress());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initAddEditProductCargoComponent() throws Exception {
		var productCargoBean = (ProductCargoBean) data;
		var listProduct = Load.createList(Service.action(Command.GET_PRODUCT_TABLE), ProductBean.class);
		var listCargo = Load.createList(Service.action(Command.GET_CARGO_TABLE), CargoBean.class);
		cProduct.setItems(FXCollections.observableArrayList(listProduct));
		cCargo.setItems(FXCollections.observableArrayList(listCargo));
		if (command == Command.UPDATE_PRODUCTCARGO) {
			tWeight.setText(Double.toString(productCargoBean.getWeight()));
			tCustomsDuty.setText(Double.toString(productCargoBean.getCustomsDuty()));
			cProduct.getSelectionModel().select(productCargoBean.getProduct());
			cCargo.getSelectionModel().select(productCargoBean.getCargo());
		} else {
			cProduct.getSelectionModel().selectFirst();
			cCargo.getSelectionModel().selectFirst();
		}
		cProduct.setConverter(new StringConverter<ProductBean>() {
			@Override
			public String toString(ProductBean object) {
				if (object != null) {
					return object.getName();
				}
				return null;
			}

			@Override
			public ProductBean fromString(String string) {
				return null;
			}
		});
		cCargo.setConverter(new StringConverter<CargoBean>() {
			@Override
			public String toString(CargoBean object) {
				if (object != null) {
					return object.getUuid();
				}
				return null;
			}

			@Override
			public CargoBean fromString(String string) {
				return null;
			}
		});
	}

	private void initAddEditCargoComponent() throws Exception {
		var cargoBean = (CargoBean) data;
		dDate.setValue(LocalDate.now());
		var listPostBean = Load.createList(Service.action(Command.GET_POST_TABLE), PostBean.class);
		cPost.setItems(FXCollections.observableArrayList(listPostBean));
		if (command == Command.UPDATE_CARGO) {
			cPost.getSelectionModel().select(cargoBean.getPost());
			dDate.setValue(LocalDate.parse(cargoBean.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			tUuid.setText(cargoBean.getUuid());
		} else {
			cPost.getSelectionModel().selectFirst();
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
