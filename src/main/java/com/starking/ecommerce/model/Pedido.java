package com.starking.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.hibernate.annotations.LazyToOne;
//import org.hibernate.annotations.LazyToOneOption;

import com.starking.ecommerce.model.enums.StatusPedido;
import com.starking.ecommerce.model.listener.GenericoListener;
import com.starking.ecommerce.model.listener.GerarNotaFiscalListener;

import lombok.*;

@Getter
@Setter
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
@Entity
@Table(name = "pedido")
@Cacheable
public class Pedido extends EntidadeBaseInteger 
//implements PersistentAttributeInterceptable
{

	@NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name="fk_pedido_cliente"))
    private Cliente cliente;

	@NotEmpty
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ItemPedido> itens;

	@PastOrPresent
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

	@PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

	@PastOrPresent
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

//	@LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Positive
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

//    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @Embedded
    private Endereco enderecoEntrega;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

//    @PrePersist
//    @PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
        	total = BigDecimal.ZERO;
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Ap�s persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Ap�s atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Ap�s remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Ap�s carregar o Pedido.");
    }
    
//    public NotaFiscal getNotaFiscal() {
//    	if (this.attributeInterceptor != null) {
//    		return (NotaFiscal) attributeInterceptor.readObject(this, "notaFiscal", this.notaFiscal);
//    	}
//    	
//    	return this.notaFiscal;
//    }
//    
//	public void setNotaFiscal(NotaFiscal notaFiscal) {
//		if (this.attributeInterceptor != null) {
//			this.notaFiscal = (NotaFiscal) attributeInterceptor.writeObject(this, "notaFiscal", this.notaFiscal, notaFiscal);
//		} else {			
//			this.notaFiscal = notaFiscal;
//		}
//	}
//    
//	public Pagamento getPagamento() {
//		if (this.attributeInterceptor != null) {
//			return (Pagamento) attributeInterceptor.readObject(this, "pagamento", this.pagamento);
//		}
//		return this.pagamento;
//	}
//    
//	public void setPagamento(Pagamento pagamento) {
//		if (this.attributeInterceptor != null) {
//			this.pagamento = (Pagamento) attributeInterceptor.writeObject(this, "pagamento", this.pagamento, pagamento);
//		} else {
//			this.pagamento = pagamento;
//		}
//	}
//    
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @Transient
//    private PersistentAttributeInterceptor attributeInterceptor;
//
//	@Override
//	public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
//		return this.attributeInterceptor;
//	}
//
//	@Override
//	public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {
//		this.attributeInterceptor = interceptor;
//	} 
}
