package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

	private Integer id;

	 @Column(name = "nome")
	private String nome;

	private Integer categoria_id;
}
