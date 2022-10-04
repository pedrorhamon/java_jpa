package com.starking.ecommerce.criteria;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.NotaFiscal;
import com.starking.ecommerce.model.Pedido;

public class PassandoParametrosCriteriaTest extends EntityManagerTest{
	
	@Test
	public void passarParametroDate() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<NotaFiscal> criteriaQuery = criteriaBuilder.createQuery(NotaFiscal.class);
		Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);

		criteriaQuery.select(root);

		ParameterExpression<Date> parameterExpression = criteriaBuilder.parameter(Date.class);
		
		criteriaQuery.where(criteriaBuilder.greaterThan(root.get("dataEmissao"), parameterExpression));
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.add(Calendar.DATE, -30);

		TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setParameter("dataInicial", dataInicial.getTime(), TemporalType.TIMESTAMP);

		List<NotaFiscal> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void passarParametro() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);

		ParameterExpression<Integer> parameterExpression = criteriaBuilder.parameter(Integer.class);
		
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), parameterExpression));

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setParameter("id", 1);

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

}
