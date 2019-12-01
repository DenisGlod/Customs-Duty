package com.server.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductCargo implements Serializable {

	private static final long serialVersionUID = -1172214187709867725L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@Column(nullable = false)
	private Double weight;

	@Column(scale = 2)
	private Double customsDuty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
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
