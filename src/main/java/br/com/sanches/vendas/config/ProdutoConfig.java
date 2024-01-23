package br.com.sanches.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sanches.vendas.adapters.out.produto.ProdutoAdapter;
import br.com.sanches.vendas.application.core.usecase.produto.ProdutoUseCase;

@Configuration
public class ProdutoConfig {

	@Bean
	public ProdutoUseCase produtoUseCase(ProdutoAdapter produtoAdapter) {
		return new ProdutoUseCase(produtoAdapter);
	}

}
