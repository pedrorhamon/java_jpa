package com.starking.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

	@NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name="fk_estoque_produto"))
    private Produto produto;

    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "integer not null", nullable = false)
    private Integer quantidade;
}
