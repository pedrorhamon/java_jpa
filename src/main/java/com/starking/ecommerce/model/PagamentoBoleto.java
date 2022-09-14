package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.starking.ecommerce.model.enums.StatusPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger{

//    @EqualsAndHashCode.Include
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
