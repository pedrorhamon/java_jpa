package com.starking.ecommerce.service;

import com.starking.ecommerce.model.Pedido;

public class NotaFiscalService {

	public void gerar(Pedido pedido) {
		System.out.println("Gerar nota pedido" + pedido.getId());
	}
}
