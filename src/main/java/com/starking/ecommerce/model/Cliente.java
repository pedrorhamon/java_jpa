package com.starking.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.starking.ecommerce.model.enums.SexoCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@NamedStoredProcedureQuery(name = "compraram_acima_media", procedureName = "compraram_acima_media",
	parameters = {
			@StoredProcedureParameter(name = "ano", type = Integer.class, mode = ParameterMode.IN)
	},
     resultClasses = Cliente.class
)
@Setter
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@Entity
@Table(name = "cliente", uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = { "cpf" }) },
indexes = { @Index(name = "idx_nome", columnList = "nome") })
public class Cliente extends EntidadeBaseInteger {

//	@Id
//	@EqualsAndHashCode.Include
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@NotBlank(message = "Nome obrigatório")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@CPF
	@NotNull(message = "CPF obrigatório")
	@Column(length = 14, nullable = false)
    private String cpf;
	
	@Transient
	private String primeiroNome;
	
	@Column(name = "data_nascimento", table = "cliente_detalhe")
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo_cliente", length = 30, nullable = false)
	private SexoCliente sexoCliente;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@ElementCollection
	@CollectionTable(name = "cliente_contato", 
	joinColumns = @JoinColumn(name="cliente_id", nullable = false, foreignKey = @ForeignKey(name="fk_contato_cliente")))
	@MapKeyColumn(name = "tipo")
	@Column(name = "descricao")
	private Map<String, String> contatos;
	
	@PostLoad
	public void configurarPrimeiroNome() {
		if(nome != null && !nome.isBlank()) {
			int index = nome.indexOf(" ");
			if(index > -1) {
				primeiroNome = nome.substring(0, index);
			}
		}
	}
}
