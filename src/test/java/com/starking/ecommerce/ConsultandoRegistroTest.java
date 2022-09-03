package com.starking.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starking.ecommerce.model.Produto;

public class ConsultandoRegistroTest {
	
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManager manager;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}
	
	@After
	public void tearDown() {
		manager.close();
	}
	
	@Test
	public void buscarPoridentificador() {
//		Produto produto = manager.find(Produto.class, 1);
		Produto produto = manager.getReference(Produto.class, 1);
		
		Assert.assertNotNull(produto);
		Assert.assertEquals("Kindle", produto.getNome());
	}
	
	@Test
	public void atualizarReferencia() {
		Produto produto = manager.find(Produto.class, 1);
		produto.setNome("Philips");
		
		Assert.assertEquals("Kindle", produto.getNome());
	}
}
