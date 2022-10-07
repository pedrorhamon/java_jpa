package com.starking.ecommerce.criteria;

import java.util.List;

import javax.persistence.TypedQuery;


import org.junit.Test;

import javax.persistence.criteria.*;
import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Categoria;
import com.starking.ecommerce.model.Categoria_;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Cliente_;
import com.starking.ecommerce.model.ItemPedido;
import com.starking.ecommerce.model.ItemPedido_;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Pedido_;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.Produto_;

public class GroupByCriteriaTest extends EntityManagerTest {

	@Test
    public void agruparResultadoComFuncoes() {
//         Total de vendas por mês.
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
//                " from Pedido p " +
//                " group by year(p.dataCriacao), month(p.dataCriacao) ";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Expression<Integer> anoCriacaoPedido = criteriaBuilder
                .function("year", Integer.class, root.get(Pedido_.dataCriacao));
        Expression<Integer> mesCriacaoPedido = criteriaBuilder
                .function("month", Integer.class, root.get(Pedido_.dataCriacao));
        Expression<String> nomeMesCriacaoPedido = criteriaBuilder
                .function("monthname", String.class, root.get(Pedido_.dataCriacao));

        Expression<String> anoMesConcat = criteriaBuilder.concat(
                criteriaBuilder.concat(anoCriacaoPedido.as(String.class), "/"),
                nomeMesCriacaoPedido
        );

        criteriaQuery.multiselect(
                anoMesConcat,
                criteriaBuilder.sum(root.get(Pedido_.total))
        );

        criteriaQuery.groupBy(anoCriacaoPedido, mesCriacaoPedido);

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println("Ano/Mês: " + arr[0] + ", Sum: " + arr[1]));
    }
	
	@Test
    public void agruparResultado03Exercicio() {
//        Total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.pedido p join p.cliente c " +
//                " group by c.id";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Pedido> joinPedido = root.join(ItemPedido_.pedido);
        Join<Pedido, Cliente> joinPedidoCliente = joinPedido.join(Pedido_.cliente);

        criteriaQuery.multiselect(
                joinPedidoCliente.get(Cliente_.nome),
                criteriaBuilder.sum(root.get(ItemPedido_.precoProduto))
        );
	}
	
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
