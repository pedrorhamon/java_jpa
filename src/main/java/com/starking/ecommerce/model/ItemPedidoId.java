package com.starking.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPedidoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Column(name = "pedido_id")
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pedidoId;

	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer produtoId;

}
