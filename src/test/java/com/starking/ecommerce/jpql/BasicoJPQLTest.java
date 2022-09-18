package com.starking.ecommerce.jpql;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest{

	@Test
	public void buscarPorIdentificador() {
		
		TypedQuery<Pedido> typedQuery = entityManager
				.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
		
		Pedido pedido = typedQuery.getSingleResult();
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		Assert.assertNull(pedido);
	}
	
	@Test
	public void mostrarDiferencaQueries() {
		String jpql = "select p from Pedido p where p.id = 1";
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		Pedido pedido = typedQuery.getSingleResult();
		Assert.assertNull(pedido);
		
		Query query = entityManager.createQuery(jpql);
//		Pedido pedido2 = (Pedido) query.getSingleResult();
//		Assert.assertNull(pedido2);
		
		@SuppressWarnings("unchecked")
		List<Pedido> lista = query.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
