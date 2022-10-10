package com.starking.ecommerce.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("boleto")
@Table(name="pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

	@NotBlank
    @Column(name = "codigo_barras", columnDefinition = "varchar(100) not null")
    private String codigoBarras;
    
	@NotNull
    @Column(name = "data_vencimento")	
    private LocalDate dataVencimento;
}
 