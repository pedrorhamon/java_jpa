package com.starking.ecommerce;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest{

	 @Test
	    public void removerEntidadeRelacionada() {
	        Pedido pedido = entityManager.find(Pedido.class, 1);

	        Assert.assertFalse(pedido.getItens().isEmpty());

	        entityManager.getTransaction().begin();
	        pedido.getItens().forEach(i -> entityManager.remove(i));
	        entityManager.remove(pedido);
	        entityManager.getTransaction().commit();

	        entityManager.clear();

	        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
	        Assert.assertNull(pedidoVerificacao);
	    }
}
