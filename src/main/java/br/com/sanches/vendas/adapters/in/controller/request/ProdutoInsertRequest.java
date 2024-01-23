package br.com.sanches.vendas.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoInsertRequest {

	@NotBlank
	private String descricaoProduto;
	@NotNull
	private Double valorProduto;
}
