package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	@Column(name = "cep")
	private String cep;

	@NotBlank
	@Column(name = "logradouro")
	private String logradouro;

	@NotBlank
	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@NotBlank
	@Column(name = "bairro")
	private String bairro;
	
	@NotBlank
	@Column(name = "cidade")
	private String cidade;
	
	@NotBlank
	@Size(max = 2, min = 2)
	@Column(name = "estado")
	private String estado;
}
