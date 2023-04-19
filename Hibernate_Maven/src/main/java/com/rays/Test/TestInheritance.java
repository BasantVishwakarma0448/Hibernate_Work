package com.rays.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rays.Bank.Cash;
import com.rays.Bank.Cheque;
import com.rays.Bank.CreditCard;

public class TestInheritance {

	public static void main(String[] args) {
		Cheque c = new Cheque();

		c.setAmount(45000);
		c.setChequeNo("hjh2988440");
		c.setPaymentType("Cheque");

		Cash cs = new Cash();
		cs.setAmount(90000);
		cs.setName("Cash");
		cs.setPaymentType("Cash");

		CreditCard cc = new CreditCard();

		cc.setAmount(7800000);
		cc.setCC_Type("VISA");
		cc.setPaymentType("CRedit CArd");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		session.save(c);
		session.save(cs);
		session.save(cc);

		tx.commit();
		session.close();
	}
}
