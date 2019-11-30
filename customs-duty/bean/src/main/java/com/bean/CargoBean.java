package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String uuid;

	private String date;

	private PostBean post;

	private List<ProductCargoBean> productCargos = new ArrayList<ProductCargoBean>();

	public CargoBean() {
	}

	public CargoBean(Long id, String uuid, String date, PostBean post) {
		this.id = id;
		this.uuid = uuid;
		this.date = date;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PostBean getPost() {
		return post;
	}

	public void setPost(PostBean post) {
		this.post = post;
	}

	public List<ProductCargoBean> getProductCargos() {
		return productCargos;
	}

	public void setProductCargos(List<ProductCargoBean> productCargos) {
		this.productCargos = productCargos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cargo [id=");
		builder.append(id);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", date=");
		builder.append(date);
		builder.append(", post=");
		builder.append(post);
		builder.append(", productCargos=");
		builder.append(productCargos);
		builder.append("]");
		return builder.toString();
	}

}
