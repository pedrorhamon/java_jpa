package com.starking.ecommerce;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;

public class OptionalTest extends EntityManagerTest {

	@Test
	public void verficarComportamento() {
		@SuppressWarnings("unused")
		Pedido pedido = entityManager.find(Pedido.class, 1);

	}
}
