package com.starking.ecommerce.consultasnativas;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;

public class ConsultaNativaTest extends EntityManagerTest{
	
	@Test
	public void executarSQL() {
		String sql = "select * from produto";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();
		
		lista.stream().forEach(arry -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", arry[0], arry[1])));
	}
}
