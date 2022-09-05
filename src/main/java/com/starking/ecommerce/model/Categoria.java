package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {

	@EqualsAndHashCode.Include
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @SequenceGenerator(name = "seq", sequenceName = "sequencias_chave_primaria")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
//	@TableGenerator(name = "tabela", table = "hibernate_sequences", 
//		pkColumnName = "sequence_name",
//		pkColumnValue = "categoria",
//		valueColumnName = "next_val",
//		initialValue = 0,
//		allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "categoria_pai_id")
	private Integer categoriaPaiId;

}
