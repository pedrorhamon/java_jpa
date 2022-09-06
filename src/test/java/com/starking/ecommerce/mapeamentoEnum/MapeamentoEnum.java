package com.starking.ecommerce.mapeamentoEnum;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.enums.SexoCliente;

public class MapeamentoEnum extends EntityManagerTest{
	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Pedro Rhamon");
		
		cliente.setSexoCliente(SexoCliente.MASCULINO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

		entityManager.clear();	
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
	}

}
