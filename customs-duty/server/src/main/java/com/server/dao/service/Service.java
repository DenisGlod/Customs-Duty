package com.server.dao.service;

import javax.persistence.criteria.Root;

import com.bean.ClientBean;
import com.bean.Command;
import com.bean.RoleBean;
import com.server.dao.HibernateUtil;
import com.server.dao.entity.Client;
import com.server.dao.entity.Client_;

public class Service {

	public static Object action(Command command, Object data) {
		Object responceObject = null;
		var session = HibernateUtil.getSessionFactory().openSession();
		var transaction = session.beginTransaction();
		var builder = session.getCriteriaBuilder();
		switch (command) {
		case LOGIN:
			ClientBean clientBean = (ClientBean) data;
			var criteriaLogin = builder.createQuery(Client.class);
			Root<Client> root = criteriaLogin.from(Client.class);
			criteriaLogin.select(root).where(builder.equal(root.get(Client_.LOGIN), clientBean.getLogin()),
					builder.equal(root.get(Client_.PASSWORD), clientBean.getPassword()));
			var client = session.createQuery(criteriaLogin).getResultList();

			clientBean = new ClientBean();
			if (!client.isEmpty()) {
				clientBean.setId(client.get(0).getId());
				clientBean.setLastName(client.get(0).getLastName());
				clientBean.setFirstName(client.get(0).getFirstName());
				clientBean.setMiddleName(client.get(0).getMiddleName());
				var rol = new RoleBean();
				rol.setId(client.get(0).getRole().getId());
				rol.setName(client.get(0).getRole().getName());
				clientBean.setRole(rol);
				clientBean.setStatus(client.get(0).getStatus());
			}
			responceObject = clientBean;
			break;
		case GET_USER_TABLE:
			var criteriaGUT = builder.createQuery(Client.class);
			Root<Client> rootGUT = criteriaGUT.from(Client.class);
			rootGUT.fetch(Client_.ROLE);
			criteriaGUT.select(rootGUT);
			responceObject = session.createQuery(criteriaGUT).getResultList();
			break;
		}
		transaction.commit();
		session.clear();
		return responceObject;

	}

}
