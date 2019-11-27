package com.bean;

import java.io.Serializable;

public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String login;

	private String password;

	private RoleBean role;

	private Boolean status;

	private String firstName;

	private String lastName;

	private String middleName;

	public ClientBean() {
	}

	public ClientBean(Long id, String login, String password, RoleBean role, Boolean status, String firstName,
			String lastName, String middleName) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
	}

	public boolean isEmpty() {
		if (id == null && login == null && password == null && role == null && status == null && firstName == null
				&& lastName == null && middleName == null) {
			return true;
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append(", status=");
		builder.append(status);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append("]");
		return builder.toString();
	}

}
