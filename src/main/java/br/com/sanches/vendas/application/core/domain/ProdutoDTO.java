package br.com.sanches.vendas.application.core.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO implements Serializable{
 
	private static final long serialVersionUID = 7181921574226334321L;

	private Long codigoProduto;
 
	private String descricaoProduto;
 
	private Double valorProduto;
}
