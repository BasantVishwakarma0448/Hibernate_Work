package com.rays.Test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rays.pojo.AuctionItem;
import com.rays.pojo.Bid;

public class TestAuction {
	public static void main(String[] args) {
		TestInsert();
	}

	private static void TestInsert() {

		AuctionItem item = new AuctionItem();
		item.setDescription("iPhone");

		Bid bid = new Bid();

		bid.setAmount(100);
		bid.setAmount(100);

		Bid bid1 = new Bid();
		bid1.setAmount(200);
		bid1.setAmount(200);

		Set<Bid> set = new HashSet<Bid>();

		set.add(bid1);
		set.add(bid);

		item.setBid(set);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(item);

		System.out.println("Inserted");
		tx.commit();
		session.close();

	}
}
