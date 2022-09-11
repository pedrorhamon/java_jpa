package com.starking.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(ItemPedidoId.class)
@Table(name = "item_pedido")
public class ItemPedido {

//    @EqualsAndHashCode.Include
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "pedido_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidoId;
    
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    @Column(name = "quantidade")
    private Integer quantidade;
}
