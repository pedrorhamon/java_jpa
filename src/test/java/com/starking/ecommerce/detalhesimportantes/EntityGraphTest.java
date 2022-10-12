package com.starking.ecommerce.detalhesimportantes;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;

public class EntityGraphTest extends EntityManagerTest {
	
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
