package com.starking.ecommerce.jpql;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.NotaFiscal;
import com.starking.ecommerce.model.Pedido;
import com.starking.ecommerce.model.enums.StatusPagamento;

public class PassandoParametrosTest extends EntityManagerTest {

	@Test
	public void passarParametroDate() {
		String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

		TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
		typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

		List<NotaFiscal> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}
	
	@Test
    public void passarParametro() {
        String jpql = "select p from Pedido p join p.pagamento pag " +
                " where p.id = :pedidoId and pag.status = ?1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }
}
