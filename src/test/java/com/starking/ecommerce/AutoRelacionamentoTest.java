package com.starking.ecommerce;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;

public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        Categoria categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoriaPaiId(categoriaPai);


        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaVerificacao.getCategoriaPaiId());

        Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
        Assert.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
    }
}
