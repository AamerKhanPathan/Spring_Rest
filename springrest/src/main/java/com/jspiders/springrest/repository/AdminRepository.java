package com.jspiders.springrest.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;


import com.jspiders.springrest.pojo.AdminPOJO;

@Repository
public class AdminRepository {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	public static void openConnection() {
		factory = Persistence.createEntityManagerFactory("Rest");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

	}

	public static void closeConnection() {

		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	//create account
	public AdminPOJO createAccount(AdminPOJO admin) {
		openConnection();
		transaction.begin();
		manager.persist(admin);

		transaction.commit();
		closeConnection();
		return admin;
	}

	//loginAccount
	public AdminPOJO login(AdminPOJO admin) {
		openConnection();
		transaction.begin();
		AdminPOJO pojo = manager.find(AdminPOJO.class, admin.getName());
		
		if (pojo != null) {
			if (pojo.getPassword().equals(admin.getPassword())) {
				transaction.commit();
				closeConnection();
				return pojo;
			}
		}

		transaction.commit();
		closeConnection();
		return null;
	}

}
