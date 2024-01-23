package br.com.sanches.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sanches.vendas.adapters.out.cliente.ClienteAdapter;
import br.com.sanches.vendas.application.core.usecase.cliente.ClienteUseCase;

@Configuration
public class ClienteConfig {

	@Bean
	public ClienteUseCase clienteUseCase(ClienteAdapter clienteAdapter) {
		return new ClienteUseCase(clienteAdapter);
	}
}
