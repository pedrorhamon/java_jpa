package com.starking.ecommerce.criteria;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Produto;

public class IntroCriteria extends EntityManagerTest {

	@Test
	public void projetarOResultadoTuple() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
		Root<Tuple> root = criteriaQuery.from(Tuple.class);
		
		criteriaQuery.select(criteriaBuilder.tuple(root.get("id").alias("id"), root.get("nome").alias("nome")));
		
		TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Tuple> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(t -> System.out.println("ID: " + t.get("id") + ", " + t.get("nome")));
	}
	
	@Test
	public void projetarOResultado() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Object[]> root = criteriaQuery.from(Object[].class);
		
		criteriaQuery.multiselect(root.get("id"), root.get("nome"));
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println("ID: " + arr[0] + ", " + arr[1]));
	}
	
	@Test
    public void retornarTodosOsProdutosExercicio() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }	
	
	@Test
	public void selecionarUmAtributoParaRetorno() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		criteriaQuery.select(root.get("cliente"));

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
		
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
		Cliente cliente = typedQuery.getSingleResult();
		Assert.assertEquals("Fernando Medeiros", cliente.getNome());
	}

	@Test
	public void buscarPorIdentificador() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

//		String jpql = "select sum(p.total) from Pedido p";

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
