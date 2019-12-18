package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer code;

	private String name;

	private Double percent;

	private List<ProductCargoBean> productCargos = new ArrayList<ProductCargoBean>();

	public ProductBean() {
	}

	public ProductBean(Integer code, String name, Double percent) {
		super();
		this.code = code;
		this.name = name;
		this.percent = percent;
	}

	public ProductBean(Long id, Integer code, String name, Double percent) {
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

	public List<ProductCargoBean> getProductCargos() {
		return productCargos;
	}

	public void setProductCargos(List<ProductCargoBean> productCargos) {
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
