package com.bean;

import java.io.Serializable;

public class ProductCargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ProductBean product;

	private CargoBean cargo;

	private Double weight;

	private Double customsDuty;

	public ProductCargoBean() {
	}

	public ProductCargoBean(Long id, ProductBean product, CargoBean cargo, Double weight, Double customsDuty) {
		this.id = id;
		this.product = product;
		this.cargo = cargo;
		this.weight = weight;
		this.customsDuty = customsDuty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public CargoBean getCargo() {
		return cargo;
	}

	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getCustomsDuty() {
		return customsDuty;
	}

	public void setCustomsDuty(Double customsDuty) {
		this.customsDuty = customsDuty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductCargo [id=");
		builder.append(id);
		builder.append(", product=");
		builder.append(product);
		builder.append(", cargo=");
		builder.append(cargo);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", customsDuty=");
		builder.append(customsDuty);
		builder.append("]");
		return builder.toString();
	}

}
