package com.starking.ecommerce.operacoesemcascata;

import org.junit.Assert;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedidoId;
import com.starking.ecommerce.model.Pedido;

public class CascadeTypeRemoveTest extends EntityManagerTest {
	
	public void removerPedidoEItens() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		entityManager.getTransaction().begin();
		entityManager.remove(pedido);
		entityManager.getTransaction();
		
		entityManager.clear();
		
		Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNull(pedidoVerificado);
		
	}
	
	 // @Test
    public void removerItemPedidoEPedido() {
        ItemPedido itemPedido = entityManager.find(
                ItemPedido.class, new ItemPedidoId(1, 1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido); // Necessário CascadeType.REMOVE no atributo "pedido".
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assert.assertNull(pedidoVerificacao);
    }
}