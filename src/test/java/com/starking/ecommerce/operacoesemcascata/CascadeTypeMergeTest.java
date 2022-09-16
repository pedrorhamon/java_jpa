package com.starking.ecommerce.operacoesemcascata;

import java.util.Arrays;

import org.junit.Assert;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedidoId;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.enums.StatusPedido;

public class CascadeTypeMergeTest extends EntityManagerTest {

    // @Test
    public void atualizarPedidoComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(3);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.MERGE

        entityManager.getTransaction().begin();
        entityManager.merge(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(itemPedidoVerificacao.getQuantidade().equals(3));
    }

    // @Test
    public void atualizarItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.PAGO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setPedido(pedido); // CascadeType.MERGE
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(5);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido));

        entityManager.getTransaction().begin();
        entityManager.merge(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(StatusPedido.PAGO.equals(itemPedidoVerificacao.getPedido().getStatus()));
    }
}
