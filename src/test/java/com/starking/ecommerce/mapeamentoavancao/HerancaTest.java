package com.starking.ecommerce.mapeamentoavancao;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda Morais");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao.getId());
    }
}
