package com.starking.ecommerce.model;

import java.time.LocalDate;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PagamentoBoleto.class)
public abstract class PagamentoBoleto_ extends com.starking.ecommerce.model.Pagamento_ {

	public static volatile SingularAttribute<PagamentoBoleto, String> codigoBarras;
	public static volatile SingularAttribute<PagamentoBoleto, LocalDate> dataVencimento;

	public static final String CODIGO_BARRAS = "codigoBarras";
	
	public static final String DATA_VENCIMENTO = "dataVencimento";

}

