package com.starking.ecommerce.criteria;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Pedido_;
import com.starking.ecommerce.model.Produto;
import com.starking.ecommerce.model.Produto_;

public class SubqueriesCriteriaTest extends EntityManagerTest{
	
	@Test
	public void pesquisarSubqueries02() {
//      Todos os pedidos acima da média de vendas
//     String jpql = "select p from Pedido p where " +
//             " p.total > (select avg(total) from Pedido)";

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);
		
		Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Pedido> subqueryRoot = subquery.from(Pedido.class);
        subquery.select(criteriaBuilder.avg(subqueryRoot.get(Pedido_.total)).as(BigDecimal.class));
        
        criteriaQuery.where(criteriaBuilder.greaterThan(root.get(Pedido_.total), subquery));


		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());

		lista.forEach(obj -> System.out
				.println("ID: " + obj.getId() + ", Total " + obj.getTotal()));
	}
	
	@Test
    public void pesquisarSubqueries01() {
//         Todos os pedidos acima da média de vendas
//        String jpql = "select p from Pedido p where " +
//                " p.total > (select avg(total) from Pedido)";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        
        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Produto> subqueryRoot = subquery.from(Produto.class);
        subquery.select(criteriaBuilder.max(subqueryRoot.get(Produto_.preco)));
        
        criteriaQuery.where(criteriaBuilder.equal(subqueryRoot.get(Produto_.preco), subqueryRoot));
        
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(
                "ID: " + obj.getId() + ", Total: " + obj.getTotal()));
        
	}

}
