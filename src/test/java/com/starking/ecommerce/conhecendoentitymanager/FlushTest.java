package com.starking.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.enums.StatusPedido;

public class FlushTest extends EntityManagerTest {

	@Test
	public void chamarFlush() {
		try {
			entityManager.getTransaction().begin();
			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);

//			entityManager.flush();
			
			if (pedido.getPagamento() == null) {
				throw new RuntimeException("Pedido ainda não foi pago");
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
}
