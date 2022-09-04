package com.starking.ecommerce;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Produto;

public class OperacaoComTransacaoTest extends EntityManagerTest {
	
	@Test
	public void atualizarObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
				
		entityManager.getTransaction().begin();
		produto.setNome("Kindle Tests 2");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle Tests 2", produtoVerificacao.getNome());
	}
	
	@SuppressWarnings("null")
	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();
		
		produto.setId(1);
		produto.setNome("Kindle Tests");
		produto.setDescricao("Conheça o tests");
		produto.setPreco(new BigDecimal(4500));
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNull(produtoVerificacao);
		Assert.assertEquals("Kindle Tests", produtoVerificacao.getNome());
	}

	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 1L);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = new Produto();

		produto.setId(3);
		produto.setNome("Câmera Canon");
		produto.setDescricao("A melhor definição para suas fotos.");
		produto.setPreco(new BigDecimal(5000));

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjetoComMerge() {
		Produto produto = new Produto();

		produto.setId(2);
		produto.setNome("Câmera Canon 2");
		produto.setDescricao("A melhor definição para suas fotos. testes");
		produto.setPreco(new BigDecimal(1000));

		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

	@Test
	public void abrirFecharATransacao() {
//	        Produto produto = new Produto(); // Somente para o método não mostrar erros.

		entityManager.getTransaction().begin();

//	        entityManager.persist(produto);
//	        entityManager.merge(produto);
//	        entityManager.remove(produto);

		entityManager.getTransaction().commit();
	}
	
	 @Test
	    public void impedirOperacaoComBancoDeDados() {
	        Produto produto = entityManager.find(Produto.class, 1);
	        entityManager.detach(produto);

	        entityManager.getTransaction().begin();
	        produto.setNome("Kindle Paperwhite 2ª Geração");
	        entityManager.getTransaction().commit();

	        entityManager.clear();

	        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
	        Assert.assertEquals("Kindle", produtoVerificacao.getNome());
	    }
}
