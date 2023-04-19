package com.rays.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.rays.pojo.User;

public class Testlevel {
	public static void main(String[] args) {
		// testFirstLevel();
		testQueryLevel();
	}

	private static void testQueryLevel() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");
		q.setCacheable(true);
		
		List<User> list = q.list();

		Iterator<User> it = list.iterator();
		while (it.hasNext()) {
			User user = it.next();

			System.out.print(user.getFname() + "\t");
			System.out.println(user.getLname());
		}
		// Query q1 = session.createQuery("from User where Fname like '%y'");

		List<User> list1 = q.list();

		Iterator<User> it1 = list1.iterator();
		while (it1.hasNext()) {
			User user1 = it1.next();

			System.out.print(user1.getFname() + "\t");
			System.out.println(user1.getLname());
		}
		session.close();
	}

	private static void testFirstLevel() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();
		User user = (User) session.get(User.class, 4);

		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUserName());
		System.out.println(user.getPwd());

		session.close();

		User user1 = (User) session.get(User.class, 4);

		System.out.println(user1.getFname());
		System.out.println(user1.getLname());
		System.out.println(user1.getUserName());
		System.out.println(user1.getPwd());

		session.close();

	}
}
