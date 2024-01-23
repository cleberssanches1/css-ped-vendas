package br.com.sanches.vendas.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequestInsert {

	@NotBlank
	private String nomeCliente;

	@NotBlank
	private String email;

}
