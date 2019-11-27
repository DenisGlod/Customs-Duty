package com.server;

import com.server.dao.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		var session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println(session.isConnected());
		System.out.println(session.isOpen());
		session.getTransaction().commit();
		session.close();
	}

}
