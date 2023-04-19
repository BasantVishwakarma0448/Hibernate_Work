package com.rays.Hiber5;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() {
		if (factory != null) {
			return factory;
		}

		// Set Hibernate Properties

		Map<String, Object> setting = new HashMap<String, Object>();
		setting.put("hibernate.connetion.driver_class", "com.mysql.jdbc.driver");
		setting.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hiberdb");
		setting.put("hibernate.connection.username", "root");
		setting.put("hibernate.connection.password", "root");
		setting.put("hibernate.show_sql", "true");
		setting.put("hibernate.hbm2ddl.auto", "update");
		setting.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		// Create Registry Builder

		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

		// Set Registry Properties

		registryBuilder.applySettings(setting);

		// Create Registry

		registry = registryBuilder.build();

		// Register Entity Class

		MetadataSources source = new MetadataSources(registry);

		source.addAnnotatedClass(User.class);

		Metadata metaData = source.getMetadataBuilder().build();

		// Create Session Facotry

		factory = metaData.getSessionFactoryBuilder().build();

		return factory;

	}

}
