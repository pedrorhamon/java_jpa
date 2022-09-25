package com.starking.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Pedido;

public class SubqueriesTest extends EntityManagerTest {

	@Test
	public void pesquisarComIN() {

		String jpql = "select p from Pedido p where p.id in "
				+ "(select p2.id from ItemPedido i2"
				+ " join i2.pedido p2 "
				+ "join i2.produto pro2 "
				+ "where pro2.peco > 100)";

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());

		lista.forEach(obj -> System.out.println("ID: " + obj.getId()));

	}

	@Test
	public void pesquisarSubqueries() {

//      Bons clientes. Versão 2.
		String jpql = "select c from Cliente c where 500 < (select sm(p.total) from Pedido p where p.cliente = c)";

//      Bons clientes. Versão 1.
//		String jpql = "select c from Cliente c where 500 < (select sum(p.total) from c.pedidos)";

//      Todos os pedidos acima da média de vendas
//		String jpql = "select p from Pedido p where p.total > (select avg(total) from Pedido)";

//      O produto ou os produtos mais caros da base.
//		String jpql = "select p from Produto p where p.preco = (select max(preco) from Produto)";

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

		List<Cliente> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());

		lista.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Nome: " + obj.getNome()));

	}
}
