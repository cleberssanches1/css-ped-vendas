package br.com.sanches.vendas.adapters.in.controller.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoResponse implements Serializable {

	private static final long serialVersionUID = -2905073643865947216L;

	private Long codigoProduto;

	private String descricaoProduto;

	private Double valorProduto;
}
