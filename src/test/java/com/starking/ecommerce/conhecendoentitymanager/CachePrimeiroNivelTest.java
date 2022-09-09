package com.starking.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Produto;

public class CachePrimeiroNivelTest extends EntityManagerTest{

	@Test
	public void verificarCache() {
		Produto produto = entityManager.find(Produto.class, 1);
		System.out.println(produto.getNome());
		
		System.out.println("-------------------------------");
		
		entityManager.clear();
		entityManager = entityManagerFactory.createEntityManager();
		
		Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
		
		System.out.println(produtoResgatado.getNome());
	}
}
