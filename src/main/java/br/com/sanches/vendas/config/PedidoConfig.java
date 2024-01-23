package br.com.sanches.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sanches.vendas.adapters.out.pedido.PedidoAdapter;
import br.com.sanches.vendas.application.core.usecase.pedido.PedidoUseCase;

@Configuration
public class PedidoConfig {
	@Bean
	public PedidoUseCase pedidoUseCase(PedidoAdapter pedidoAdapter) {
		return new PedidoUseCase(pedidoAdapter);
	}
}
 