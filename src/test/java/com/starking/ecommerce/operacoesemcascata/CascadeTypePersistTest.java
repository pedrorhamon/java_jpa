package com.starking.ecommerce.operacoesemcascata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedidoId;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.enums.SexoCliente;
import com.starking.ecommerce.model.enums.StatusPedido;

public class CascadeTypePersistTest extends EntityManagerTest {
	
	@Test
	public void persistirProdutoComCategoria() {
		Produto produto = new Produto();
		produto.setDataCriacao(LocalDateTime.now());
		produto.setPreco(BigDecimal.valueOf(10));
		produto.setNome("Pedro Rhamon");
		produto.setDescricao("A melhor qualidade de homem");
		
		Categoria categoria = new Categoria();
        categoria.setNome("Áudio");

        produto.setCategorias(Arrays.asList(categoria)); // CascadeType.PERSIST
		
		 entityManager.getTransaction().begin();
	        entityManager.persist(produto);
	        entityManager.getTransaction().commit();

	        entityManager.clear();

	        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
	        Assert.assertNotNull(produtoVerificacao);
	}

    // @Test
    public void persistirPedidoComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedido.getItens().isEmpty());

    }

    @Test
    public void persistirItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);// Não é necessário CascadeType.PERSIST porque possui @MapsId.
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
    }

    // @Test
    public void persistirPedidoComCliente() {
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
        cliente.setSexoCliente(SexoCliente.MASCULINO);
        cliente.setNome("José Carlos");
        cliente.setCpf("01234567890");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente); // CascadeType.PERSIST
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
}