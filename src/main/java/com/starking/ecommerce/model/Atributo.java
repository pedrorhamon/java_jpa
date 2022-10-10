package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {

	@NotBlank
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	private String valor;
}
