package com.starking.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ extends com.starking.ecommerce.model.EntidadeBaseInteger_ {

	public static volatile SingularAttribute<Produto, BigDecimal> preco;
	public static volatile SingularAttribute<Produto, Estoque> estoque;
	public static volatile ListAttribute<Produto, Categoria> categorias;
	public static volatile ListAttribute<Produto, ItemPedido> itemPedidos;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, LocalDateTime> dataUltimaAtualizacao;
	public static volatile SingularAttribute<Produto, LocalDateTime> dataCriacao;
	public static volatile ListAttribute<Produto, Atributo> atributos;
	public static volatile SingularAttribute<Produto, byte[]> fotos;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile ListAttribute<Produto, String> tags;

	public static final String PRECO = "preco";
	public static final String ESTOQUE = "estoque";
	public static final String CATEGORIAS = "categorias";
	public static final String ITEM_PEDIDOS = "itemPedidos";
	public static final String NOME = "nome";
	public static final String DATA_ULTIMA_ATUALIZACAO = "dataUltimaAtualizacao";
	public static final String DATA_CRIACAO = "dataCriacao";
	public static final String ATRIBUTOS = "atributos";
	public static final String FOTOS = "fotos";
	public static final String DESCRICAO = "descricao";
	public static final String TAGS = "tags";

}

