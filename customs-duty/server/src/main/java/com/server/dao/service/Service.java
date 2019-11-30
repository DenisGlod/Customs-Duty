package com.server.dao.service;

import java.util.ArrayList;

import com.bean.CargoBean;
import com.bean.ClientBean;
import com.bean.Command;
import com.bean.PostBean;
import com.bean.ProductBean;
import com.bean.ProductCargoBean;
import com.server.dao.HibernateUtil;
import com.server.dao.entity.Cargo;
import com.server.dao.entity.Client;
import com.server.dao.entity.Client_;
import com.server.dao.entity.Post;
import com.server.dao.entity.Product;
import com.server.dao.entity.ProductCargo;
import com.server.util.Converter;

public class Service {

	public static Object action(Command command) {
		return action(command, null);
	}

	public static Object action(Command command, Object data) {
		Object responceObject = null;
		var session = HibernateUtil.getSessionFactory().openSession();
		var transaction = session.beginTransaction();
		var builder = session.getCriteriaBuilder();
		switch (command) {
		case LOGIN:
			ClientBean clientBean = (ClientBean) data;
			var criteriaLogin = builder.createQuery(Client.class);
			var root = criteriaLogin.from(Client.class);
			criteriaLogin.select(root).where(builder.equal(root.get("login"), clientBean.getLogin()), builder.equal(root.get(Client_.PASSWORD), clientBean.getPassword()));
			var sResult = session.createQuery(criteriaLogin).getSingleResult();
			if (sResult == null) {
				responceObject = new ClientBean();
			} else {
				responceObject = Converter.convertToClientBean(sResult);
			}
			break;
		case GET_USER_TABLE:
			var criteriaGUT = builder.createQuery(Client.class);
			var rootGUT = criteriaGUT.from(Client.class);
			rootGUT.fetch(Client_.ROLE);
			criteriaGUT.select(rootGUT);
			var list = session.createQuery(criteriaGUT).getResultList();
			var resultList = new ArrayList<ClientBean>();
			list.forEach(l -> {
				resultList.add(Converter.convertToClientBean(l));
			});
			responceObject = resultList;
			break;
		case GET_CARGO_TABLE:
			var criteriaGCT = builder.createQuery(Cargo.class);
			var rootGCT = criteriaGCT.from(Cargo.class);
			criteriaGCT.select(rootGCT);
			var resultGCT = session.createQuery(criteriaGCT).getResultList();
			var reultListGCT = new ArrayList<CargoBean>();
			resultGCT.forEach(item -> {
				reultListGCT.add(Converter.convertToCargoBean(item));
			});
			responceObject = reultListGCT;
			break;
		case GET_POST_TABLE:
			var criteriaGPOSTT = builder.createQuery(Post.class);
			var rootGPOSTT = criteriaGPOSTT.from(Post.class);
			criteriaGPOSTT.select(rootGPOSTT);
			var listGPOSTT = session.createQuery(criteriaGPOSTT).getResultList();
			var resultListGPOSTT = new ArrayList<PostBean>();
			listGPOSTT.forEach(item -> {
				resultListGPOSTT.add(Converter.convertToPostBean(item));
			});
			responceObject = resultListGPOSTT;
			break;
		case GET_PRODUCTCARGO_TABLE:
			var criteriaGPCT = builder.createQuery(ProductCargo.class);
			var rootGPCT = criteriaGPCT.from(ProductCargo.class);
			criteriaGPCT.select(rootGPCT);
			var listGPCT = session.createQuery(criteriaGPCT).getResultList();
			var resultListGPCT = new ArrayList<ProductCargoBean>();
			listGPCT.forEach(item -> {
				resultListGPCT.add(Converter.convertToProductCargoBean(item));
			});
			responceObject = resultListGPCT;
			break;
		case GET_PRODUCT_TABLE:
			var criteriaGPT = builder.createQuery(Product.class);
			var rootGPT = criteriaGPT.from(Product.class);
			criteriaGPT.select(rootGPT);
			var listGPT = session.createQuery(criteriaGPT).getResultList();
			var resultListGPT = new ArrayList<ProductBean>();
			listGPT.forEach(item -> {
				resultListGPT.add(Converter.convertToProductBean(item));
			});
			responceObject = resultListGPT;
			break;
		case UPDATE_USER:
			ClientBean clientUpdate = (ClientBean) data;
			var criteriaUserUpdate = builder.createCriteriaUpdate(Client.class);
			var roortUU = criteriaUserUpdate.from(Client.class);
			criteriaUserUpdate.set(Client_.ID, clientUpdate.getId());
			criteriaUserUpdate.set(Client_.FIRST_NAME, clientUpdate.getFirstName());
			criteriaUserUpdate.set(Client_.LAST_NAME, clientUpdate.getLastName());
			criteriaUserUpdate.set(Client_.MIDDLE_NAME, clientUpdate.getMiddleName());
			criteriaUserUpdate.set(Client_.PASSWORD, clientUpdate.getPassword());
			criteriaUserUpdate.set(Client_.LOGIN, clientUpdate.getLogin());
			criteriaUserUpdate.set(Client_.ROLE, Converter.convertToRole(clientUpdate.getRole()));
			criteriaUserUpdate.set(Client_.STATUS, clientUpdate.getStatus());
			criteriaUserUpdate.where(builder.equal(roortUU.get(Client_.ID), clientUpdate.getId()));
			responceObject = session.createQuery(criteriaUserUpdate).executeUpdate();
			break;
		}
		transaction.commit();
		session.close();
		return responceObject;

	}

}
