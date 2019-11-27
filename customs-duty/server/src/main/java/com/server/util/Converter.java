package com.server.util;

import com.bean.ClientBean;
import com.server.dao.entity.Client;

public class Converter {

	private Converter() {
	}

	public static ClientBean convertToClientBean(Client client, ClientBean clientBean) {
		var result = new ClientBean();
		result.setId(client.getId());
		result.setLastName(client.getLastName());
		result.setFirstName(client.getFirstName());
		result.setMiddleName(client.getMiddleName());
		return result;
	}

}
