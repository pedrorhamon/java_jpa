package com.starking.ecommerce.criteria;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;

public class PaginacaoCriteriaTest extends EntityManagerTest {

	@Test
	public void paginarResultados() {
		String jpql = "select c from Categoria c order by c.nome";

		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);

		// FIRST_RESULT = MAX_RESULTS * (pagina - 1)
		typedQuery.setFirstResult(6);
		typedQuery.setMaxResults(2);

		List<Categoria> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());

		lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
	}
}