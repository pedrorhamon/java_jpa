package com.starking.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;

public class JoinTest extends EntityManagerTest {

	@Test
	public void fazerLeftJoin() {
		String jpql = "select p, pag from Pedido p left join p.pagamento pag";

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}

	@Test
	public void fazerJoin() {
		String jpql = "select p, pag from Pedido p inner join p.pagamento pag";

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
