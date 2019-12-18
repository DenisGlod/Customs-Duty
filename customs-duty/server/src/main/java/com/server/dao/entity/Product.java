package com.server.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = -9011500708430868486L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer code;

	@Column(nullable = false)
	private String name;

	@Column(scale = 2)
	private Double percent;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductCargo> productCargos = new ArrayList<ProductCargo>();

	public Product() {
		super();
	}

	public Product(Long id, Integer code, String name, Double percent) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.percent = percent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public List<ProductCargo> getProductCargos() {
		return productCargos;
	}

	public void setProductCargos(List<ProductCargo> productCargos) {
		this.productCargos = productCargos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", percent=");
		builder.append(percent);
		builder.append(", productCargos=");
		builder.append(productCargos);
		builder.append("]");
		return builder.toString();
	}

}
