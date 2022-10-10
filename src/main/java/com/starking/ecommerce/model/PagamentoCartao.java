package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("cartao")
@Table(name="pagamento_cartao")
public class PagamentoCartao extends Pagamento {

	@NotBlank
    @Column(name = "numero_cartao", columnDefinition = "varchar(100) not null")
    private String numeroCartao;
}
