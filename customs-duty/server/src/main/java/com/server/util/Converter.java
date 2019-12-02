package com.server.util;

import java.text.DateFormat;
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
		return new ProductBean(product.getId(), product.getCode(), product.getName());
	}

	public static CargoBean convertToCargoBean(Cargo item) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return new CargoBean(item.getId(), item.getUuid(), formatter.format(item.getDateCargo()), convertToPostBean(item.getPost()));
	}

	public static PostBean convertToPostBean(Post post) {
		return new PostBean(post.getId(), post.getName(), post.getAdress());
	}

	public static ProductCargoBean convertToProductCargoBean(ProductCargo item) {
		return new ProductCargoBean(item.getId(), convertToProductBean(item.getProduct()), convertToCargoBean(item.getCargo()), item.getWeight(), item.getCustomsDuty());
	}

	public static Role convertToRole(RoleBean roleBean) {
		var role = new Role();
		role.setId(roleBean.getId());
		role.setName(roleBean.getName());
		return role;
	}

	public static Client convertToClient(ClientBean clientUpdate) {
		return new Client(clientUpdate.getId(), clientUpdate.getLogin(), clientUpdate.getPassword(), convertToRole(clientUpdate.getRole()), clientUpdate.getStatus(), clientUpdate.getFirstName(),
				clientUpdate.getLastName(), clientUpdate.getMiddleName());
	}

	public static Product convertToProduct(ProductBean data) {
		var product = new Product();
		product.setId(data.getId());
		product.setCode(data.getCode());
		product.setName(data.getName());
		return product;
	}

	public static Cargo convertToCargo(CargoBean bean) {
		try {
			return new Cargo(bean.getId(), bean.getUuid(), DateFormat.getInstance().parse(bean.getDate()), convertToPost(bean.getPost()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Post convertToPost(PostBean post) {
		return new Post(post.getId(), post.getName(), post.getAdress());
	}

}
