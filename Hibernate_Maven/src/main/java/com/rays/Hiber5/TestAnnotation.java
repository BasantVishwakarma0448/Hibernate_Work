package com.rays.Hiber5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TestAnnotation {
	public static void main(String[] args) {
		User user = new User();

		user.setFirstName("Basant");
		user.setLastName("Vishwakarma");

		SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

		Session session = sessionfactory.openSession();

		Transaction tx = session.beginTransaction();

		session.save(user);

		tx.commit();
		session.close();
		sessionfactory.close();
	}
}
