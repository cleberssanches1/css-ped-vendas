package br.com.sanches.vendas.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoRequest {
	@NotNull
	private Long codigoProduto;
	@NotBlank
	private String descricaoProduto;
	@NotNull
	private Double valorProduto;
}
