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

	private static final long serialVersionUID = -4074736077736632917L;

	@EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer pedidoId;

    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Integer produtoId;
}
