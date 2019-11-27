package com.server.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = 8985421175576932165L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String adress;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Cargo> cargos = new ArrayList<Cargo>();

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

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void addCargo(Cargo cargo) {
		cargos.add(cargo);
		cargo.setPost(this);
	}

	public void removeCargo(Cargo cargo) {
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
