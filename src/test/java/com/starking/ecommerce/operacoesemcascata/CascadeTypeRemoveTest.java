package com.starking.ecommerce.operacoesemcascata;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedidoId;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Produto;

public class CascadeTypeRemoveTest extends EntityManagerTest {
	
	// @Test
    public void removerItensOrfaos() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
    }
	
	@Test
	public void removerRelacaoProdutoCategoria() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Assert.assertFalse(produto.getCategorias().isEmpty());
		
		entityManager.getTransaction().begin();
		produto.getCategorias().clear();
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
	}
	
	@Test
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