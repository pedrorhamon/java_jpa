package com.starking.ecommerce.multitenat;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.hibernate.EcmCurrentTenantIdentifierResolver;
import com.starking.ecommerce.init.EntityManagerFactoryTest;
import com.starking.ecommerce.model.Produto;

public class MultiTenantTest extends EntityManagerFactoryTest {

	@Test
	public void usarAbordagemPorSchema() {
		EcmCurrentTenantIdentifierResolver.setTenantIdentifier("starking_ecommerce");
		EntityManager entityManager1 = entityManagerFactory.createEntityManager();
		Produto produto1 = entityManager1.find(Produto.class, 1);
		Assert.assertEquals("Kindle", produto1.getNome());
		entityManager1.close();

		EcmCurrentTenantIdentifierResolver.setTenantIdentifier("loja_ecommerce");
		EntityManager entityManager2 = entityManagerFactory.createEntityManager();
		Produto produto2 = entityManager2.find(Produto.class, 1);
		Assert.assertEquals("Kindle Paperwhite", produto2.getNome());
		entityManager2.close();
	}
}
