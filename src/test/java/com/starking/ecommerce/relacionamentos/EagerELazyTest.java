package com.starking.ecommerce.relacionamentos;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        pedido.getItens().isEmpty();
    }
}
