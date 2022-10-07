package com.starking.ecommerce.criteria;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;
import com.starking.ecommerce.model.Categoria_;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedido_;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.Produto_;

public class GroupByCriteriaTest extends EntityManagerTest {

	@Test
	public void agruparResultado02() {
//	        Total de vendas por categoria.
//	        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//	                " join ip.produto pro join pro.categorias c " +
//	                " group by c.id";

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
		Join<ItemPedido, Produto> joinProduto = root.join(ItemPedido_.produto);
		Join<Produto, Categoria> joinProdutoCategoria = joinProduto.join(Produto_.categorias);

		criteriaQuery.multiselect(joinProdutoCategoria.get(Categoria_.nome),
				criteriaBuilder.sum(root.get(ItemPedido_.precoProduto)));

		criteriaQuery.groupBy(joinProdutoCategoria.get(Categoria_.id));

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Object[]> lista = typedQuery.getResultList();

		lista.forEach(arr -> System.out.println("Nome categoria: " + arr[0] + ", Sum: " + arr[1]));

	}

	@Test
	public void agruparResultado01() {
//	        Quantidade de produtos por categoria.
//	        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Categoria> root = criteriaQuery.from(Categoria.class);
		Join<Categoria, Produto> joinProduto = root.join(Categoria_.produtos, JoinType.LEFT);

		criteriaQuery.multiselect(root.get(Categoria_.nome), criteriaBuilder.count(joinProduto.get(Produto_.id)));

		criteriaQuery.groupBy(root.get(Categoria_.id));

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Object[]> lista = typedQuery.getResultList();

		lista.forEach(arr -> System.out.println("Nome: " + arr[0] + ", Count: " + arr[1]));
	}

}
