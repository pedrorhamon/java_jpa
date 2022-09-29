package com.starking.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name = "Produto.listar", query = "select p from Produto p"),
	@NamedQuery(name = "Produto.listarPorCategoria", query = "select p from Produto p where exists (select 1 from Categoria c2 join c2.produtos p2 where p2 = p and c2.id = :categoria)"),
})
@Table(name = "produto", uniqueConstraints = { 
		@UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) },
indexes = { @Index(name = "idx_nome", columnList = "nome") })
public class Produto extends EntidadeBaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@Lob
	private String descricao;

	@Column(name = "preco", precision = 19,scale = 2)
	private BigDecimal preco;
	
	@Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

	@OneToMany
	private List<ItemPedido> itemPedidos; 
	
	@ManyToMany
	@JoinTable(name = "produto_categoria", 
	joinColumns = @JoinColumn(name="produto_id", nullable = false, foreignKey = @ForeignKey(name="fk_produto_categoria_produto")),
	inverseJoinColumns = @JoinColumn(name="categoria_id", nullable = false, foreignKey = @ForeignKey(name="fk_produto_categoria_categoria")))
	private List<Categoria> categorias;
	
	@OneToOne(mappedBy = "produto")
	private Estoque estoque;
	
	@ElementCollection
	@CollectionTable(name = "produto_tag",
	joinColumns = @JoinColumn(name="produto_id", nullable = false, foreignKey = @ForeignKey(name="fk_tags_produto")))
	@Column(name = "tag", columnDefinition = "varchar(50) not null")
	private List<String> tags;
	
	@ElementCollection
	@CollectionTable(name="produto_atributo", 
	joinColumns = @JoinColumn(name="produto_id", nullable = false, foreignKey = @ForeignKey(name="fk_atributo_produto")))
	private List<Atributo> atributos;
	
	@Lob
	private byte[] fotos;
}
