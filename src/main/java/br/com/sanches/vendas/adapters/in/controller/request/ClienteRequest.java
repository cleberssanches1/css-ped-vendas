package br.com.sanches.vendas.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequest {

	private Long codigoCliente;
	@NotBlank
	private String nomeCliente;
	@NotBlank
	private String email;

}
