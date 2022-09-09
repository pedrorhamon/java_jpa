package com.starking.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.enums.StatusPedido;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

	@Test(expected = Exception.class)
	public void abrirFecharCancelarTransacao() {
		try {
			entityManager.getTransaction().begin();
			this.metodoDeNegocio();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}

	private void metodoDeNegocio() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		pedido.setStatus(StatusPedido.PAGO);

		if (pedido.getPagamentoCartao() != null) {
			throw new RuntimeException("Pedido ainda não foi pago");
		}
	}
}
