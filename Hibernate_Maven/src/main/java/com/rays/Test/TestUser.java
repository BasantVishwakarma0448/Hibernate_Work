package com.rays.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rays.pojo.User;

public class TestUser {
	public static void main(String[] args) {
		// testadd();
		TestMerge();
	}

	private static void TestMerge() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		User user = null;
		user = session.get(User.class, 1);

		System.out.println(user.getFname());

		session.close();
		Session session1 = factory.openSession();
		Transaction tx = session1.beginTransaction();

		user.setFname("Basant");

		session1.merge(user);
		tx.commit();
		session1.close();

	}

	private static void testadd() {
		User user = new User();

		user.setFname("Virendra");
		user.setLname("Vishwaakarma");
		user.setPwd("vi@123");
		user.setUserName("virendra123@gmail.com");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		System.out.println("Inserted");
	}
}
