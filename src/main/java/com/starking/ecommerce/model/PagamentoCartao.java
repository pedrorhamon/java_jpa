package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.starking.ecommerce.model.enums.StatusPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "status")
    private StatusPagamento status;

    @Column(name = "numero")
    private String numero;
}
