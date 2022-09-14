package com.starking.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadeBaseInteger{

//	@EqualsAndHashCode.Include
//	@Id
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
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "categoria_pai_id")
	private Categoria categoriaPaiId;

	@OneToMany(mappedBy = "categoriaPaiId")
	private List<Categoria> categorias;
	
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;
}
