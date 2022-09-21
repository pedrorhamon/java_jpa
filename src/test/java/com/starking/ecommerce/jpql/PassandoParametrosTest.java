package com.starking.ecommerce.jpql;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.NotaFiscal;

public class PassandoParametrosTest extends EntityManagerTest {

	@Test
	public void passarParametroDate() {
		String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

		TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
		typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

		List<NotaFiscal> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}
}
