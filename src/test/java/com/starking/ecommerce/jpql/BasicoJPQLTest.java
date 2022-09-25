package com.starking.ecommerce.jpql;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.dto.ProdutoDTO;

public class BasicoJPQLTest extends EntityManagerTest{
	
	@Test
	public void usarDistinct() {
		String jpql = "select distinct p from Pedido p " + " join p.itens i join i.produto pro "
				+ " where pro.id in (1, 2, 3, 4) ";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.isEmpty());

		System.out.println(lista.size());

	}
	
	@Test
	public void ordenarResultados() {
		String jpql = "select c from Cliente c order by c.nome asc";

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
		List<Cliente> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.isEmpty());

		lista.forEach(p -> System.out.println(p.getId() + ", " + p.getNome()));
	}
	
	@Test
	public void projetarDTO() {
		String jpql = "select new com.starking.ecommerce.model.dto.ProdutoDTO(id, nome) from Produto";
		
		TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
		List<ProdutoDTO> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.isEmpty());
		
		lista.forEach(p -> System.out.println(p.getId() + ", " + p.getNome()));
	}
	
	@Test
	public void projetarOResultado() {
		String jpql = "select id, nome from Produto";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertTrue(lista.get(0).length ==2);
		
		lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
	}
	
	@Test
	public void selecionarUmAtributoParaRetorno() {
		String jpql = "select p.nome from Produto p";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
		List<String> lista = typedQuery.getResultList();
		Assert.assertTrue(String.class.equals(lista.get(0).getClass()));
		
		String jpqlCliente = "select p.cliente from Pedido p";
		TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
		List<Cliente> listaClientes = typedQueryCliente.getResultList();
		Assert.assertTrue(Cliente.class.equals(listaClientes.get(0).getClass()));
	}

	@Test
	public void buscarPorIdentificador() {
		
		TypedQuery<Pedido> typedQuery = entityManager
				.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
		
		Pedido pedido = typedQuery.getSingleResult();
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		Assert.assertNull(pedido);
	}
	
	@Test
	public void mostrarDiferencaQueries() {
		String jpql = "select p from Pedido p where p.id = 1";
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		Pedido pedido = typedQuery.getSingleResult();
		Assert.assertNull(pedido);
		
		Query query = entityManager.createQuery(jpql);
//		Pedido pedido2 = (Pedido) query.getSingleResult();
//		Assert.assertNull(pedido2);
		
		@SuppressWarnings("unchecked")
		List<Pedido> lista = query.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
