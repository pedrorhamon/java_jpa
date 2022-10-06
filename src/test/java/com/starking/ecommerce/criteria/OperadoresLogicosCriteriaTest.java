package com.starking.ecommerce.criteria;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Cliente;
import com.starking.ecommerce.model.Cliente_;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.Pedido_;
import com.starking.ecommerce.model.enums.StatusPedido;

public class OperadoresLogicosCriteriaTest extends EntityManagerTest {
	
	@Test
    public void ordenarResultado() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Cliente_.nome)));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(
                "ID: " + c.getId() + ", Total: " + c.getNome()));
    }
	
	@Test
    public void usarOperadores() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        // select p from Pedido p where (status = 'PAGO' or status = 'AGUARDANDO') and total > 499

        criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(
                                root.get(Pedido_.status), StatusPedido.AGUARDANDO),
                        criteriaBuilder.equal(
                                root.get(Pedido_.status), StatusPedido.PAGO)
                ),
                criteriaBuilder.greaterThan(
                        root.get(Pedido_.total), new BigDecimal(499))
        );

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println(
                "ID: " + p.getId() + ", Total: " + p.getTotal()));
    }
}
