package com.starking.ecommerce.cache;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starking.ecommerce.model.Pedido;

public class CacheTest {
	
	protected static EntityManagerFactory entityManagerFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}
	
	@Test
	public void removerDoCache() {
		Cache cache = entityManagerFactory.getCache();

		EntityManager entityManager1 = entityManagerFactory.createEntityManager();
		EntityManager entityManager2 = entityManagerFactory.createEntityManager();

		System.out.println("Buscando a partir da instância 1:");
		entityManager1.createQuery("select p from Pedido p", Pedido.class).getResultList();

		System.out.println("Removendo do cache");
		cache.evictAll();
//	        cache.evict(Pedido.class);
//	        cache.evict(Pedido.class, 1);

		System.out.println("Buscando a partir da instância 2:");
		entityManager2.find(Pedido.class, 1);
		entityManager2.find(Pedido.class, 2);
	}
	
	@Test
    public void adicionarPedidosNoCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        entityManager1.createQuery("select p from Pedido p", Pedido.class).getResultList();
        System.out.println("Buscando a partir da instância 2:");
        entityManager2.find(Pedido.class, 1);
    }
	
	@Test
    public void buscarDoCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        entityManager1.find(Pedido.class, 1);

        System.out.println("Buscando a partir da instância 2:");
        entityManager2.find(Pedido.class, 1);
    }
}