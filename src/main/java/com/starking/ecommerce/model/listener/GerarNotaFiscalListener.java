package com.starking.ecommerce.model.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.service.NotaFiscalService;

public class GerarNotaFiscalListener {
	
	private NotaFiscalService notaFiscalService = new NotaFiscalService();

	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if (pedido.isPago() && pedido.getNotaFiscal() == null) {
			this.notaFiscalService.gerar(pedido);
		}
	}
}
