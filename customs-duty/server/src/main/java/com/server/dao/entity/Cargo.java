package com.server.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cargo implements Serializable {

	private static final long serialVersionUID = 311320456318160683L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String uuid;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCargo;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
	private List<ProductCargo> productCargos = new ArrayList<ProductCargo>();

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

	public Date getDateCargo() {
		return dateCargo;
	}

	public void setDateCargo(Date dateCargo) {
		this.dateCargo = dateCargo;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
		builder.append("Cargo [id=");
		builder.append(id);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", dateCargo=");
		builder.append(dateCargo);
		builder.append(", post=");
		builder.append(post);
		builder.append(", productCargos=");
		builder.append(productCargos);
		builder.append("]");
		return builder.toString();
	}

}
