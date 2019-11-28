package com.server.util;

import com.bean.ClientBean;
import com.bean.RoleBean;
import com.server.dao.entity.Client;
import com.server.dao.entity.Role;

public class Converter {

	private Converter() {
	}

	public static ClientBean convertToClientBean(Client client) {
		var clientBean = new ClientBean(client.getId(), client.getLogin(), client.getPassword(),
				convertToRoleBean(client.getRole()), client.getStatus(), client.getFirstName(), client.getLastName(),
				client.getMiddleName());
		return clientBean;
	}

	public static RoleBean convertToRoleBean(Role role) {
		return new RoleBean(role.getId(), role.getName());
	}

}
