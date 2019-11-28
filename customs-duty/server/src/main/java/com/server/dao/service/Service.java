package com.server.dao.service;

import java.util.ArrayList;

import javax.persistence.criteria.Root;

import com.bean.ClientBean;
import com.bean.Command;
import com.server.dao.HibernateUtil;
import com.server.dao.entity.Client;
import com.server.dao.entity.Client_;
import com.server.util.Converter;

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
			var sResult = session.createQuery(criteriaLogin).getSingleResult();
			if (sResult == null) {
				responceObject = new ClientBean();
			} else {
				responceObject = Converter.convertToClientBean(sResult);
			}
			break;
		case GET_USER_TABLE:
			var criteriaGUT = builder.createQuery(Client.class);
			Root<Client> rootGUT = criteriaGUT.from(Client.class);
			rootGUT.fetch(Client_.ROLE);
			criteriaGUT.select(rootGUT);
			var list = session.createQuery(criteriaGUT).getResultList();
			var resultList = new ArrayList<ClientBean>();
			list.forEach(l -> {
				resultList.add(Converter.convertToClientBean(l));
			});
			responceObject = resultList;
			break;
		}
		transaction.commit();
		session.clear();
		return responceObject;

	}

}
