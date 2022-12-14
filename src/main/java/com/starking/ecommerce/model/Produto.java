package com.starking.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.starking.ecommerce.model.converter.BooleanToSimNaoConverter;
import com.starking.ecommerce.model.dto.ProdutoDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "produto_loja.listar",
            query = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto " +
                    " from produto_loja", resultClass = Produto.class),
    @NamedNativeQuery(name = "ecm_produto.listar",
            query = "select * from ecm_produto", resultSetMapping = "ecm_produto.Produto")
})
@SqlResultSetMappings({
	@SqlResultSetMapping(name = "produto_loja.Produto", entities = {@EntityResult(entityClass = Produto.class)}),
	@SqlResultSetMapping(name = "ecm_produto.Produto", 
	entities = {@EntityResult(entityClass = Produto.class, fields = {
			@FieldResult(name = "id", column = "prd_id"),
			@FieldResult(name = "nome", column = "prd_nome"),
			@FieldResult(name = "descricao", column = "prd_descricao"),
			@FieldResult(name = "preco", column = "prd_preco"),
			@FieldResult(name = "foto", column = "prd_foto"),
			@FieldResult(name = "dataCriacao", column = "prd_dataCriacao"),
			@FieldResult(name = "dataUltimaAtualizacao", column = "prd_dataCriacao")
	}) }),
@SqlResultSetMapping(name = "ecm_produto.ProdutoDTO", classes = {
		@ConstructorResult(targetClass = ProdutoDTO.class,
				columns = {
					@ColumnResult(name = "prd_id", type = Integer.class),
					@ColumnResult(name = "prd_nome", type = String.class)	
		})
	})
})
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name = "Produto.listar", query = "select p from Produto p"),
	@NamedQuery(name = "Produto.listarPorCategoria", query = "select p from Produto p where exists (select 1 from Categoria c2 join c2.produtos p2 where p2 = p and c2.id = :categoria)"),
})
@Table(name = "produto", uniqueConstraints = { 
		@UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) },
indexes = { @Index(name = "idx_nome", columnList = "nome") })
@NamedEntityGraphs({
	@NamedEntityGraph(name = "Pedido.dadosEssenciais", attributeNodes = {
			@NamedAttributeNode("dataCriacao"),
			@NamedAttributeNode("status"),
			@NamedAttributeNode("total"),
			@NamedAttributeNode(value = "cliente", subgraph = "cli")
	},
			subgraphs = {
					@NamedSubgraph(name = "cli", attributeNodes = {
							@NamedAttributeNode("nome"),
							@NamedAttributeNode("cpf")
					})
			})

})
public class Produto extends EntidadeBaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;

//	@EqualsAndHashCode.Include
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@NotBlank
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@NotBlank
	@Lob
	private String descricao;

	@Positive
	@Column(name = "preco", precision = 19,scale = 2)
	private BigDecimal preco;
	
	@PositiveOrZero
	@Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

	@PositiveOrZero
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
	
	@Convert(converter = BooleanToSimNaoConverter.class)
	@NotNull
	@Column(length = 3, updatable = false)
	private Boolean ativo = Boolean.FALSE;
}
