package com.starking.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.starking.ecommerce.model.enums.SexoCliente;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class Cliente {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;
	
	@Transient
	private String primeiroNome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo_cliente")
	private SexoCliente sexoCliente;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@PostLoad
	public void configurarPrimeiroNome() {
		if(nome != null && !nome.isBlank()) {
			int index = nome.indexOf(" ");
			if(index > -1) {
				primeiroNome = nome.substring(0, index);
			}
		}
	}
}
