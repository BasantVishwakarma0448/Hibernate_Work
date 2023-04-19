package com.rays.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rays.pojo.Address;
import com.rays.pojo.Employee;

public class TestOne2One {
	public static void main(String[] args) {
		testOne();
	}

	private static void testOne() {

		Address add = new Address();

		add.setStreet("Scheme 78");
		add.setCity("Indore");
		add.setState("madhyaPradesh");

		Employee emp = new Employee();

		emp.setName("Basant Vishwakarma");
		emp.setAddress(add);

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		session.save(emp);
		System.out.println("Inserted");
		tx.commit();
		session.close();

	}
}
