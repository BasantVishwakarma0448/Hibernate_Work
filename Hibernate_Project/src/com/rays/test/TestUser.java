package com.rays.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.rays.pojo.User;

public class TestUser {
	public static void main(String[] args) {
		// testadd();
		// testupdate();
		// testselect();
		// testlist();
		// testgetOne();
		// testgetMultiple();
		// testOrderBy();
		// testLimit();
		// testCrioteria();
		testFirstLevelcache();
	}

	private static void testFirstLevelcache() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", 2));
		List list = criteria.list();

		Iterator<User> it = list.iterator();

		while (it.hasNext()) {
			User user = it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd() + "\n");
		}

		while (it.hasNext()) {
			User user = it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd() + "\n");
		}

		session.close();
	}

	// This method is used for all the operations of Criterias
	// Criteria + Restriction + Projection
	// Restriction is used for Where Clause
	// Restriction is used for selecting specific data from database like
	// first_name, last_name
	private static void testCrioteria() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(User.class);
//		criteria.add(Restrictions.like("Fname", "%t"));
//		//criteria.add(Restrictions.eq("id", 2));
//
//		ProjectionList proj = Projections.projectionList();
//
//		proj.add(Projections.property("Fname"));
//		proj.add(Projections.property("id"));

		// criteria.setMaxResults(5);

//		criteria.setProjection(proj);
		criteria.addOrder(Order.asc("Lname"));
		List list = criteria.list();

		Iterator it = list.iterator();
		User user = null;
		while (it.hasNext()) {
			user = (User) it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd());
		}
		session.close();

	}

	private static void testLimit() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createSQLQuery("select * from User limit 0, 1");
		List list = q.list();

		Iterator it = list.iterator();
		User user = null;
		while (it.hasNext()) {
			user = (User) it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd());
		}
		session.close();

	}

	private static void testOrderBy() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User where pwd like '%e%' order by pwd");

		List list = q.list();

		Iterator it = list.iterator();
		User user = null;
		while (it.hasNext()) {
			user = (User) it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd());
		}
		session.close();

	}

	private static void testgetMultiple() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("select u.Fname, u.Lname from User u");

		List list = q.list();

		Iterator it = list.iterator();
		// User user = null;
		while (it.hasNext()) {
			Object[] user = (Object[]) it.next();

			System.out.print(user[0] + " " + user[1] + "\n");
		}
		session.close();

	}

	private static void testgetOne() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("select u.Fname from User u");

		List list = q.list();

		Iterator it = list.iterator();
		// User user = null;
		while (it.hasNext()) {
			String user = (String) it.next();

			System.out.print(user + " ");
		}
		session.close();

	}

	private static void testlist() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");

		List<User> list = q.list();

		Iterator<User> it = list.iterator();
		User user = null;
		while (it.hasNext()) {
			user = it.next();

			System.out.print(user.getId() + "\t");
			System.out.print(user.getFname() + "\t");
			System.out.print(user.getLname() + "\t");
			System.out.print(user.getUserName() + "\t");
			System.out.println(user.getPwd());
		}
		session.close();

	}

	private static void testselect() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		User user = (User) session.get(User.class, 4);

		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUserName());
		System.out.println(user.getPwd());

		session.close();
	}

	private static void testupdate() {
		User user = new User();

		user.setId(3);
		user.setFname("Lucky");
		user.setLname("Mehta");
		user.setPwd("fe@123");
		user.setUserName("raneeraj448@gmail.com");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
		System.out.println("Inserted");

	}

	private static void testadd() {
		User user = new User();

		user.setFname("Lucky");
		user.setLname("Mehta");
		user.setPwd("fe@123");
		user.setUserName("raneeraj448@gmail.com");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		System.out.println("Inserted");
	}
}
