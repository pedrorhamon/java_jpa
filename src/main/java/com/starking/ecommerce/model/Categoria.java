package com.starking.ecommerce.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
	
	private Integer id;
	
	private Integer produtoId;
	
	private Integer quantidade;

}
