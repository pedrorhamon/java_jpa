package com.starking.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Produto;

public class NamedQueryTest extends EntityManagerTest {

	@Test
    public void executarConsultaArquivoXMLEspecificoProduto() {
        TypedQuery<Produto> typedQuery = entityManager
                .createNamedQuery("Produto.todos", Produto.class);

        List<Produto> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
	
	@Test
	public void executarConsulta() {
		TypedQuery<Produto> typedQuery = entityManager
				.createNamedQuery("Produto.listarPorCategoria", Produto.class);
		typedQuery.setParameter("categoria", 2);

		List<Produto> lista = typedQuery.getResultList();

		Assert.assertFalse(lista.isEmpty());
	}
}
