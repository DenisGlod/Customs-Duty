package com.clinet.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.Command;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.ProductCargoBean;
import com.clinet.connector.Service;
import com.clinet.error.AlterMessageBox;
import com.clinet.util.Load;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminController implements Initializable {

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
		SettingsController.show(cb);
	}

	public static void show(ClientBean clientBean) {
		cb = clientBean;
		try {
			var stage = new Stage();
			stage.centerOnScreen();
			stage.setScene(new Scene(Load.loadFXML("admin.fxml")));
			stage.setResizable(true);
			stage.setMinWidth(615);
			stage.setMinHeight(440);
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
		lFullName.setText(cb.getLastName() + " " + cb.getFirstName() + " " + cb.getMiddleName());
		lRole.setText(cb.getRole().getName());
		tabSelectedChanged();
		tabUserTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabProductTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabCargoTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabProductCargoTable.selectedProperty().addListener(listener -> tabSelectedChanged());
		tabPostTable.selectedProperty().addListener(listener -> tabSelectedChanged());
	}

	public void tabSelectedChanged() {
		try {
			for (Tab tab : tabPane.getTabs()) {
				if (tab.isSelected()) {
					switch (tab.getId()) {
					case "tabUserTable":
						tableUsers.getColumns().clear();
						var listClientBean = createList(Service.action(Command.GET_USER_TABLE), ClientBean.class);
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
						break;
					case "tabProductTable":
						tableProduct.getColumns().clear();
						var listProduct = createList(Service.action(Command.GET_PRODUCT_TABLE), ProductBean.class);
						tableProduct.setItems(FXCollections.observableArrayList(listProduct));

						var idProduct = new TableColumn<ProductBean, String>("№");
						var code = new TableColumn<ProductBean, String>("Код товара");
						var name = new TableColumn<ProductBean, String>("Наименование");

						idProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
						code.setCellValueFactory(new PropertyValueFactory<>("code"));
						name.setCellValueFactory(new PropertyValueFactory<>("name"));

						tableProduct.getColumns().add(idProduct);
						tableProduct.getColumns().add(code);
						tableProduct.getColumns().add(name);
						break;
					case "tabCargoTable":
						tableCargo.getColumns().clear();
						var listCargot = createList(Service.action(Command.GET_CARGO_TABLE), CargoBean.class);
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
						break;
					case "tabProductCargoTable":
						tableProductCargo.getColumns().clear();
						var listProductCargo = createList(Service.action(Command.GET_PRODUCTCARGO_TABLE), ProductCargoBean.class);
						tableProductCargo.setItems(FXCollections.observableArrayList(listProductCargo));

						var idProductCargo = new TableColumn<ProductCargoBean, String>("№");
						var productName = new TableColumn<ProductCargoBean, String>("Наименование товара");
						var cargoUUID = new TableColumn<ProductCargoBean, String>("UUID груза");
						var weight = new TableColumn<ProductCargoBean, String>("Вес (кг)");
						var customsDuty = new TableColumn<ProductCargoBean, String>("Налог (BYN)");

						idProductCargo.setCellValueFactory(new PropertyValueFactory<>("id"));
						productName.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getProduct(), "name"));
						cargoUUID.setCellValueFactory(cellData -> Bindings.selectString(cellData.getValue().getCargo(), "uuid"));
						weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
						customsDuty.setCellValueFactory(new PropertyValueFactory<>("customsDuty"));

						tableProductCargo.getColumns().add(idProductCargo);
						tableProductCargo.getColumns().add(productName);
						tableProductCargo.getColumns().add(cargoUUID);
						tableProductCargo.getColumns().add(weight);
						tableProductCargo.getColumns().add(customsDuty);
						break;
					case "tabPostTable":
						tablePost.getColumns().clear();
						var listPost = createList(Service.action(Command.GET_POST_TABLE), PostBean.class);
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
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private <T> List<T> createList(Object data, Class<T> ct) {
		var clientList = new ArrayList<T>();
		if (data instanceof ArrayList<?>) {
			var al = (ArrayList<?>) data;
			for (Object client : al) {
				if (ct.isInstance(client)) {
					clientList.add(ct.cast(client));
				}
			}
		}
		return clientList;
	}

}
