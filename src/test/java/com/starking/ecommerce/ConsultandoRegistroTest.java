package com.starking.ecommerce;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Produto;

public class ConsultandoRegistroTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1L);
//        Produto produto = entityManager.getReference(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1L);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }
    
    @Test
    public void mostrarDifencaPersistMerge() {
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5L);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido.");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacaoPersist);

        Produto produtoMerge = new Produto();

        produtoMerge.setId(6L);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria.");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
		produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);
    }
}
