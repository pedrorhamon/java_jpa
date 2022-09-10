package com.starking.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.starking.ecommerce.model.enums.StatusPedido;
import com.starking.ecommerce.model.listener.GenericoListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners({GenericoListener.class})
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_pedido", updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao", insertable = false)
    private LocalDateTime dataConclusao;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    
    @OneToOne(mappedBy = "pedido")
    @JoinColumn(name = "pagamento_cartao")
    private PagamentoCartao pagamentoCartao;
    
    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;
    
    @Embedded
    private Endereco endereco;
    
    @OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidos; 
    
    @PrePersist
    public void aoPersistir() {
    	dataConclusao = LocalDateTime.now();
    	calcularTotal();
    }
    
    @PreUpdate
    public void aoAtualizar() {
    	dataPedido = LocalDateTime.now();
    	calcularTotal();
    }
    
    @PrePersist
    @PreUpdate
    public void calcularTotal() {
    	if(itemPedidos != null) {
    		total = itemPedidos.stream()
    				.map(ItemPedido::getPrecoProduto)
    				.reduce(BigDecimal.ZERO, BigDecimal::add);
    	}
    }
    
    public boolean isPago() {
    	return StatusPedido.PAGO.equals(status);
    }
}
