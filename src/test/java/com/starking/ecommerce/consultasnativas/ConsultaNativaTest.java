package com.starking.ecommerce.consultasnativas;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.starking.ecommerce.init.EntityManagerTest;
import com.starking.ecommerce.model.Produto;

public class ConsultaNativaTest extends EntityManagerTest{
	
	@Test
	public void passarParametros() {
		String sql = "select id, nome, descricao, " +
                "            null data_criacao, null data_ultima_atualizacao, " +
                "            preco, null foto " +
                " from ecm_produto where prd_id = :id";
		
		Query query = entityManager.createNativeQuery(sql, Produto.class);
		query.setParameter("id", 1);
		
		List<Produto> lista = query.getResultList();
		
		lista.stream().forEach(obj -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
	}
	
	@Test
	public void executarSQLRetornandoEntidade() {
//      String sql = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto " +
//      " from produto_loja";

//String sql = "select prd_id id, prd_nome nome, prd_descricao descricao, " +
//      "            prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
//      "            prd_preco preco, prd_foto foto " +
//      " from ecm_produto";

		String sql = "select id, nome, descricao, " +
                "            null data_criacao, null data_ultima_atualizacao, " +
                "            preco, null foto " +
                " from erp_produto";
		Query query = entityManager.createNativeQuery(sql, Produto.class);
		
		List<Produto> lista = query.getResultList();
		
		lista.stream().forEach(obj -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
	}
	
	@Test
	public void executarSQL() {
		String sql = "select * from produto";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();
		
		lista.stream().forEach(arry -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", arry[0], arry[1])));
	}
}
