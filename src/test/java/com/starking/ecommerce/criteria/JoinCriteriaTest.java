package com.starking.ecommerce.criteria;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.Pagamento;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.enums.StatusPagamento;

public class JoinCriteriaTest extends EntityManagerTest {
	
	@Test
	public void usarJoinFetch() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		Join<Pedido, Cliente> join = (Join<Pedido, Cliente>) root.<Pedido, Cliente>fetch("clientes");
		root.fetch("notaFiscal", JoinType.LEFT);
		root.fetch("pagamento", JoinType.LEFT);
		
		criteriaQuery.select(root);
		
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
		Cliente cliente = typedQuery.getSingleResult();
		Assert.assertNotNull(cliente);
		Assert.assertFalse(cliente.getPedidos().isEmpty());
	}
	
	@Test
	public void fazerLeftOuterJoin() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		Join<Pedido, Pagamento> joinPagamento = root.join("pagamento", JoinType.LEFT);
		
		criteriaQuery.select(root);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 4);
	}
	
	@Test
	public void fazerJoinComOn() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
		Join<Pedido, ItemPedido> joinItens = root.join("itens");
//      Join<ItemPedido, Produto> joinItemProduto = joinItens.join("produto");
		joinPagamento.on(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));
		
		criteriaQuery.select(root);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 4);
	}
	
	@Test
	public void fazerJoin() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
		Join<Pedido, ItemPedido> joinItens = root.join("itens");
//      Join<ItemPedido, Produto> joinItemProduto = joinItens.join("produto");
		
		criteriaQuery.select(root);
		
//		criteriaQuery.select(joinPagamento);
//		criteriaQuery.where(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 4);
	}
 }
