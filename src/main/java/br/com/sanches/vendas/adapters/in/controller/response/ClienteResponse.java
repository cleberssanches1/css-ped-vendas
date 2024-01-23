package br.com.sanches.vendas.adapters.in.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse {

	private Long codigoCliente;

	private String nomeCliente;

	private String email;
}
