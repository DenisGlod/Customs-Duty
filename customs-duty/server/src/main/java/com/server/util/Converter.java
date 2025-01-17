package com.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.ProductCargoBean;
import com.bean.RoleBean;
import com.server.dao.entity.Cargo;
import com.server.dao.entity.Client;
import com.server.dao.entity.Post;
import com.server.dao.entity.Product;
import com.server.dao.entity.ProductCargo;
import com.server.dao.entity.Role;

public class Converter {

	private Converter() {
	}

	public static ClientBean convertToClientBean(Client client) {
		return new ClientBean(client.getId(), client.getLogin(), client.getPassword(), convertToRoleBean(client.getRole()), client.getStatus(), client.getFirstName(), client.getLastName(),
				client.getMiddleName());
	}

	public static RoleBean convertToRoleBean(Role role) {
		return new RoleBean(role.getId(), role.getName());
	}

	public static ProductBean convertToProductBean(Product product) {
		return new ProductBean(product.getId(), product.getCode(), product.getName(), product.getPercent());
	}

	public static CargoBean convertToCargoBean(Cargo item) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return new CargoBean(item.getId(), item.getUuid(), formatter.format(item.getDateCargo()), convertToPostBean(item.getPost()));
	}

	public static PostBean convertToPostBean(Post post) {
		return new PostBean(post.getId(), post.getName(), post.getAdress());
	}

	public static ProductCargoBean convertToProductCargoBean(ProductCargo item) {
		return new ProductCargoBean(item.getId(), convertToProductBean(item.getProduct()), convertToCargoBean(item.getCargo()), item.getWeight(), item.getCost(), item.getCustomsDuty());
	}

	public static Role convertToRole(RoleBean roleBean) {
		return new Role(roleBean.getId(), roleBean.getName());
	}

	public static Client convertToClient(ClientBean clientUpdate) {
		return new Client(clientUpdate.getId(), clientUpdate.getLogin(), clientUpdate.getPassword(), convertToRole(clientUpdate.getRole()), clientUpdate.getStatus(), clientUpdate.getFirstName(),
				clientUpdate.getLastName(), clientUpdate.getMiddleName());
	}

	public static Product convertToProduct(ProductBean data) {
		return new Product(data.getId(), data.getCode(), data.getName(), data.getPercent());
	}

	public static Cargo convertToCargo(CargoBean bean) {
		try {
			return new Cargo(bean.getId(), bean.getUuid(), new SimpleDateFormat("dd.MM.yyyy").parse(bean.getDate()), convertToPost(bean.getPost()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Post convertToPost(PostBean post) {
		return new Post(post.getId(), post.getName(), post.getAdress());
	}

	public static ProductCargo convertToProductCargo(ProductCargoBean bean) {
		return new ProductCargo(bean.getId(), Converter.convertToProduct(bean.getProduct()), Converter.convertToCargo(bean.getCargo()), bean.getWeight(), bean.getCost(), bean.getCustomsDuty());
	}

}
