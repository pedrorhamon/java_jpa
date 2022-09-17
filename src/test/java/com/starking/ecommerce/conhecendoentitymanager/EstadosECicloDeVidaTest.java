package com.starking.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @SuppressWarnings("unused")
	@Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria();
        categoriaNovo.setNome("Eletrônicos");

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(categoriaGerenciada);
        entityManager.persist(categoriaGerenciada);

        entityManager.detach(categoriaGerenciada);
    }
}
