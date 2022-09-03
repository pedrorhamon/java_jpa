package com.starking.ecommerce.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IniciarUnidadePersistence {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("Ecommerce-PU");
		EntityManager manager = entityManagerFactory.createEntityManager();
		
		entityManagerFactory.close();
		manager.close();
		
	}
}
