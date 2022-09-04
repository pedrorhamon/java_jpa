package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "estoque")
public class Estoque {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "categoria_id")
	private Integer categoria_id;
}
