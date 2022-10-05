package com.starking.ecommerce.criteria;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Cliente_;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Pedido_;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.Produto_;

public class ExpresoesCondicionaisCriteriaTest extends EntityManagerTest {

	@Test
	public void usarBetween() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.between(
						root.get(Pedido_.dataCriacao), 
						LocalDateTime.now()
						.minusDays(5).withSecond(0).withHour(0), 
						LocalDateTime.now()));

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(p -> System.out.println("ID: " + p.getDataCriacao() + ", " + p.getDataConclusao()));
	}
	
	@Test
	public void usarMaiorMenorComDatas() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Pedido_.dataCriacao), LocalDateTime.now().minusDays(3)));

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarMaiorMenor() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(799)),
				criteriaBuilder.lessThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(3500)));

		TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());

		lista.forEach(p -> System.out.println("ID: " + p.getId() + ", " + p.getPreco()));
	}

	@Test
	public void usarIsEmpty() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);

		criteriaQuery.select(root);

//        criteriaQuery.where(root.get(Produto_.categorias).isEmpty());

		criteriaQuery.where(criteriaBuilder.isEmpty(root.get(Produto_.categorias)));

		TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarIsNull() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);

		criteriaQuery.select(root);

//        criteriaQuery.where(root.get(Produto_.fotos).isNull());

		criteriaQuery.where(criteriaBuilder.isNull(root.get(Produto_.fotos)));

		TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarExpressaoCondicionalLike() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.like(root.get(Cliente_.nome), "%a%"));

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Cliente> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
