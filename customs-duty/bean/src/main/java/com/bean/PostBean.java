package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String adress;

	private List<CargoBean> cargos = new ArrayList<CargoBean>();

	public PostBean() {
	}

	public PostBean(String name, String adress) {
		super();
		this.name = name;
		this.adress = adress;
	}

	public PostBean(Long id, String name, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<CargoBean> getCargos() {
		return cargos;
	}

	public void setCargos(List<CargoBean> cargos) {
		this.cargos = cargos;
	}

	public void addCargo(CargoBean cargo) {
		cargos.add(cargo);
		cargo.setPost(this);
	}

	public void removeCargo(CargoBean cargo) {
		cargos.remove(cargo);
		cargo.setPost(null);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", adress=");
		builder.append(adress);
		builder.append(", cargos=");
		builder.append(cargos);
		builder.append("]");
		return builder.toString();
	}

}
