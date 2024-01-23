package br.com.sanches.vendas.adapters.in.controller.mapper;

import br.com.sanches.vendas.adapters.in.controller.request.ProdutoInsertRequest;
import br.com.sanches.vendas.adapters.in.controller.request.ProdutoRequest;
import br.com.sanches.vendas.adapters.in.controller.response.ProdutoResponse;
import br.com.sanches.vendas.adapters.out.repository.entity.ProdutoEntity;
import br.com.sanches.vendas.application.core.domain.ProdutoDTO;

public class ProdutoMapper {

	private ProdutoMapper() {
	}

	public static ProdutoDTO fromProdutoRequestToProdutoDTO(ProdutoRequest produtoRequest) {

		return ProdutoDTO.builder().descricaoProduto(produtoRequest.getDescricaoProduto())
				.codigoProduto(produtoRequest.getCodigoProduto()).valorProduto(produtoRequest.getValorProduto())
				.build();
	}

	public static ProdutoDTO fromProdutoEntityToProdutoDTO(ProdutoEntity produtoEntity) {

		return ProdutoDTO.builder().descricaoProduto(produtoEntity.getDescricaoProduto())
				.codigoProduto(produtoEntity.getCodigoProduto()).valorProduto(produtoEntity.getValorProduto()).build();
	}

	public static ProdutoEntity fromProdutoRequestToProdutoDTO(ProdutoDTO produtoDTO) {

		return ProdutoEntity.builder().descricaoProduto(produtoDTO.getDescricaoProduto())
				.codigoProduto(produtoDTO.getCodigoProduto()).valorProduto(produtoDTO.getValorProduto()).build();
	}

	public static ProdutoResponse fromProdutoDtoToProdutResponse(ProdutoDTO produtoDTO) {

		return ProdutoResponse.builder().descricaoProduto(produtoDTO.getDescricaoProduto())
				.codigoProduto(produtoDTO.getCodigoProduto()).valorProduto(produtoDTO.getValorProduto()).build();
	}

	public static ProdutoDTO fromProdutoRequestToProdutoDTO(ProdutoInsertRequest produtoInsertRequest) {

		return ProdutoDTO.builder().descricaoProduto(produtoInsertRequest.getDescricaoProduto())
				.valorProduto(produtoInsertRequest.getValorProduto()).build();
	}

}
