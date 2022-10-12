package com.starking.ecommerce.detalhesimportantes;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Cliente_;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Pedido_;

public class EntityGraphTest extends EntityManagerTest {
	
	@Test
	public void buscarAtributosEssencialDePedido03() {
		EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
		entityGraph.addAttributeNodes(Pedido_.dataCriacao, Pedido_.status, Pedido_.total);
		
		Subgraph<Cliente> subgraphCliente = entityGraph.addSubgraph(Pedido_.cliente);
		subgraphCliente.addAttributeNodes(Cliente_.nome, Cliente_.cpf);
		
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("javax.persistence.fetchgraph", entityGraph);
//		properties.put("javax.persistence.loadgraph", entityGraph);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p", Pedido.class);
		typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
//		Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//		Assert.assertNotNull(pedido);
	}
	
	
	@Test
	public void buscarAtributosEssencialDePedido02() {
		EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
		entityGraph.addAttributeNodes("dataCriacao", "status", "total");
		
		Subgraph<Cliente> subgraphCliente = entityGraph.addSubgraph("cliente", Cliente.class);
		subgraphCliente.addAttributeNodes("nome", "cpf");
		
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("javax.persistence.fetchgraph", entityGraph);
//		properties.put("javax.persistence.loadgraph", entityGraph);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p", Pedido.class);
		typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
//		Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//		Assert.assertNotNull(pedido);
	}
	
	@Test
	public void buscarAtributosEssencialDePedido() {
		EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
		entityGraph.addAttributeNodes("dataCriacao", "status", "total", "cliente");
		
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("javax.persistence.fetchgraph", entityGraph);
//		properties.put("javax.persistence.loadgraph", entityGraph);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p", Pedido.class);
		typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
//		Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//		Assert.assertNotNull(pedido);
	}
}
