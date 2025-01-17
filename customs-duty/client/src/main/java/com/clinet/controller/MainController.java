package com.clinet.controller;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.Command;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.ProductCargoBean;
import com.clinet.App;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	private static ClientBean cb;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabUserTable;
	@FXML
	private Tab tabProductTable;
	@FXML
	private Tab tabCargoTable;
	@FXML
	private Tab tabProductCargoTable;
	@FXML
	private Tab tabPostTable;

	@FXML
	private TableView<ClientBean> tableUsers;
	@FXML
	private TableView<ProductBean> tableProduct;
	@FXML
	private TableView<CargoBean> tableCargo;
	@FXML
	private TableView<ProductCargoBean> tableProductCargo;
	@FXML
	private TableView<PostBean> tablePost;

	@FXML
	private Label lRole;
	@FXML
	private Label lFullName;

	@FXML
	private AnchorPane anchorPain;
	@FXML
	private ImageView img;

	@FXML
	private void onUserSettingsAction(ActionEvent event) {
		SettingsController.show(cb);
		tabSelectedChanged();
	}

	@FXML
	private void onAddAction(ActionEvent event) {
		try {
			AddEditController controller = null;
			for (Tab tab : tabPane.getTabs()) {
				if (tab.isSelected()) {
					switch (tab.getId()) {
					case "tabUserTable":
						controller = showAddEditForm(Command.ADD_USER, null);
						break;
					case "tabProductTable":
						controller = showAddEditForm(Command.ADD_PRODUCT, null);
						break;
					case "tabCargoTable":
						controller = showAddEditForm(Command.ADD_CARGO, null);
						break;
					case "tabProductCargoTable":
						controller = showAddEditForm(Command.ADD_PRODUCTCARGO, null);
						break;
					case "tabPostTable":
						controller = showAddEditForm(Command.ADD_POST, null);
						break;
					}
					if (controller.status == -1) {
						tabSelectedChanged();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	@FXML
	private void onDeleteAction(ActionEvent event) {
		try {
			int flag = 0;
			for (Tab tab : tabPane.getTabs()) {
				if (tab.isSelected()) {
					switch (tab.getId()) {
					case "tabUserTable":
						var clientBeanDelete = tableUsers.getSelectionModel().getSelectedItem();
						flag = (int) Service.action(Command.DELETE_USER, clientBeanDelete);
						break;
					case "tabProductTable":
						var productBean = tableProduct.getSelectionModel().getSelectedItem();
						flag = (int) Service.action(Command.DELETE_PRODUCT, productBean);
						break;
					case "tabCargoTable":
						var cargoBean = tableCargo.getSelectionModel().getSelectedItem();
						flag = (int) Service.action(Command.DELETE_CARGO, cargoBean);
						break;
					case "tabProductCargoTable":
						var productCargoBean = tableProductCargo.getSelectionModel().getSelectedItem();
						flag = (int) Service.action(Command.DELETE_PRODUCTCARGO, productCargoBean);
						break;
					case "tabPostTable":
						var postBean = tablePost.getSelectionModel().getSelectedItem();
						flag = (int) Service.action(Command.DELETE_POST, postBean);
						break;
					}
				}
			}
			tabSelectedChanged();
			if (flag == 1) {
				AlterMessageBox.showInfo("Успешно удалено");
			} else {
				AlterMessageBox.showError("Ошибка!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	@FXML
	private void onEditAction(ActionEvent event) {
		try {
			AddEditController controller = null;
			for (Tab tab : tabPane.getTabs()) {
				if (tab.isSelected()) {
					switch (tab.getId()) {
					case "tabUserTable":
						if (!tableUsers.getSelectionModel().getSelectedItems().isEmpty()) {
							controller = showAddEditForm(Command.UPDATE_USER, tableUsers.getSelectionModel().getSelectedItem());
						}
						break;
					case "tabProductTable":
						if (!tableProduct.getSelectionModel().getSelectedItems().isEmpty()) {
							controller = showAddEditForm(Command.UPDATE_PRODUCT, tableProduct.getSelectionModel().getSelectedItem());
						}
						break;
					case "tabCargoTable":
						if (!tableCargo.getSelectionModel().getSelectedItems().isEmpty()) {
							controller = showAddEditForm(Command.UPDATE_CARGO, tableCargo.getSelectionModel().getSelectedItem());
						}
						break;
					case "tabProductCargoTable":
						if (!tableProductCargo.getSelectionModel().getSelectedItems().isEmpty()) {
							controller = showAddEditForm(Command.UPDATE_PRODUCTCARGO, tableProductCargo.getSelectionModel().getSelectedItem());
						}
						break;
					case "tabPostTable":
						if (!tablePost.getSelectionModel().getSelectedItems().isEmpty()) {
							controller = showAddEditForm(Command.UPDATE_POST, tablePost.getSelectionModel().getSelectedItem());
						}
						break;
					}
					if (controller != null && controller.status == -1) {
						tabSelectedChanged();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

	private AddEditController showAddEditForm(Command command, Object data) {
		AddEditController returnController = null;
		try {
			FXMLLoader loader = null;
			var stage = new Stage();
			stage.centerOnScreen();
			switch (command) {
			case ADD_USER:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditUser.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_USER:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditUser.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			case ADD_PRODUCT:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditProduct.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_PRODUCT:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditProduct.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			case ADD_CARGO:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditCargo.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_CARGO:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditCargo.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			case ADD_PRODUCTCARGO:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditProductCargo.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_PRODUCTCARGO:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditProductCargo.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			case ADD_POST:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditPost.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Добавить");
				stage.getIcons().add(Load.loadImage("add.png"));
				break;
			case UPDATE_POST:
				loader = new FXMLLoader(App.class.getResource("/fxml/AddEditPost.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.setTitle("Изменить");
				stage.getIcons().add(Load.loadImage("edit.png"));
				break;
			default:
				break;
			}
			stage.setResizable(false);
			returnController = loader.getController();
			returnController.show(command, data, stage);
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
		return returnController;
	}

	public void show(ClientBean clientBean) {
		cb = clientBean;
		switch (cb.getRole().getName()) {
		case "Администратор":
			lFullName.setText(cb.getLastName() + " " + cb.getFirstName() + " " + cb.getMiddleName());
			lRole.setText(cb.getRole().getName());
			tabUserTable.selectedProperty().addListener(listener -> tabSelectedChanged());
			break;
		case "Пользователь":
			anchorPain.setStyle("-fx-background-color: #abdbb1;");
			lFullName.setText(cb.getLastName() + " " + cb.getFirstName() + " " + cb.getMiddleName());
			lRole.setText(cb.getRole().getName());
			tabPane.getTabs().remove(0);
			img.setImage(Load.loadImage("user.png"));
			break;
		}
		tabProductTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabCargoTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabProductCargoTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabPostTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabSelectedChanged();
	}

	public void tabSelectedChanged() {
		try {
			for (Tab tab : tabPane.getTabs()) {
				if (tab.isSelected()) {
					switch (tab.getId()) {
					case "tabUserTable":
						tableUsers.getColumns().clear();
						var listClientBean = Load.createList(Service.action(Command.GET_USER_TABLE), ClientBean.class);
						tableUsers.setItems(FXCollections.observableArrayList(listClientBean));

						var id = new TableColumn<ClientBean, String>("№");
						var login = new TableColumn<ClientBean, String>("Логин");
						var password = new TableColumn<ClientBean, String>("Пароль");
						var role = new TableColumn<ClientBean, String>("Роль");
						var status = new TableColumn<ClientBean, String>("Статус");
						var lastName = new TableColumn<ClientBean, String>("Фамилия");
						var firstName = new TableColumn<ClientBean, String>("Имя");
						var middleName = new TableColumn<ClientBean, String>("Отчество");

						id.setCellValueFactory(new PropertyValueFactory<>("id"));
						login.setCellValueFactory(new PropertyValueFactory<>("login"));
						password.setCellValueFactory(new PropertyValueFactory<>("password"));
						role.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getRole(), "name"));
						status.setCellValueFactory(new PropertyValueFactory<>("status"));
						firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
						lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
						middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));

						tableUsers.getColumns().add(id);
						tableUsers.getColumns().add(login);
						tableUsers.getColumns().add(password);
						tableUsers.getColumns().add(role);
						tableUsers.getColumns().add(status);
						tableUsers.getColumns().add(lastName);
						tableUsers.getColumns().add(firstName);
						tableUsers.getColumns().add(middleName);
						// tableUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						break;
					case "tabProductTable":
						tableProduct.getColumns().clear();
						var listProduct = Load.createList(Service.action(Command.GET_PRODUCT_TABLE), ProductBean.class);
						tableProduct.setItems(FXCollections.observableArrayList(listProduct));

						var idProduct = new TableColumn<ProductBean, String>("№");
						var code = new TableColumn<ProductBean, String>("Код товара");
						var name = new TableColumn<ProductBean, String>("Наименование");
						var percent = new TableColumn<ProductBean, String>("Процент (%)");

						idProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
						code.setCellValueFactory(new PropertyValueFactory<>("code"));
						name.setCellValueFactory(new PropertyValueFactory<>("name"));
						percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

						tableProduct.getColumns().add(idProduct);
						tableProduct.getColumns().add(code);
						tableProduct.getColumns().add(name);
						tableProduct.getColumns().add(percent);
						// tableProduct.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						break;
					case "tabCargoTable":
						tableCargo.getColumns().clear();
						var listCargot = Load.createList(Service.action(Command.GET_CARGO_TABLE), CargoBean.class);
						tableCargo.setItems(FXCollections.observableArrayList(listCargot));

						var idCargo = new TableColumn<CargoBean, String>("№");
						var uuid = new TableColumn<CargoBean, String>("UUID");
						var date = new TableColumn<CargoBean, String>("Дата");
						var post = new TableColumn<CargoBean, String>("Таможнный пункт");

						idCargo.setCellValueFactory(new PropertyValueFactory<>("id"));
						uuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
						date.setCellValueFactory(new PropertyValueFactory<>("date"));
						post.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getPost(), "name"));

						tableCargo.getColumns().add(idCargo);
						tableCargo.getColumns().add(uuid);
						tableCargo.getColumns().add(date);
						tableCargo.getColumns().add(post);
						// tableCargo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						break;
					case "tabProductCargoTable":
						tableProductCargo.getColumns().clear();
						var listProductCargo = Load.createList(Service.action(Command.GET_PRODUCTCARGO_TABLE), ProductCargoBean.class);
						tableProductCargo.setItems(FXCollections.observableArrayList(listProductCargo));

						var idProductCargo = new TableColumn<ProductCargoBean, String>("№");
						var productName = new TableColumn<ProductCargoBean, String>("Наименование товара");
						var cargoUUID = new TableColumn<ProductCargoBean, String>("UUID груза");
						var weight = new TableColumn<ProductCargoBean, String>("Вес (кг)");
						var cost = new TableColumn<ProductCargoBean, String>("Цена");
						var customsDuty = new TableColumn<ProductCargoBean, String>("Налог (BYN)");

						idProductCargo.setCellValueFactory(new PropertyValueFactory<>("id"));
						productName.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getProduct(), "name"));
						cargoUUID.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getCargo(), "uuid"));
						weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
						cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
						customsDuty.setCellValueFactory(new PropertyValueFactory<>("customsDuty"));

						tableProductCargo.getColumns().add(idProductCargo);
						tableProductCargo.getColumns().add(productName);
						tableProductCargo.getColumns().add(cargoUUID);
						tableProductCargo.getColumns().add(weight);
						tableProductCargo.getColumns().add(cost);
						tableProductCargo.getColumns().add(customsDuty);
						// tableProductCargo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						break;
					case "tabPostTable":
						tablePost.getColumns().clear();
						var listPost = Load.createList(Service.action(Command.GET_POST_TABLE), PostBean.class);
						tablePost.setItems(FXCollections.observableArrayList(listPost));

						var idPost = new TableColumn<PostBean, String>("№");
						var namePost = new TableColumn<PostBean, String>("Наименование");
						var adress = new TableColumn<PostBean, String>("Адресс");

						idPost.setCellValueFactory(new PropertyValueFactory<>("id"));
						namePost.setCellValueFactory(new PropertyValueFactory<>("name"));
						adress.setCellValueFactory(new PropertyValueFactory<>("adress"));

						tablePost.getColumns().add(idPost);
						tablePost.getColumns().add(namePost);
						tablePost.getColumns().add(adress);
						// tablePost.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlterMessageBox.showError(e.getMessage());
		}
	}

}
