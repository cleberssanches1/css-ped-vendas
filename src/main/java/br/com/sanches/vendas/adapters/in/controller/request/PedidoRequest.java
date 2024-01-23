package br.com.sanches.vendas.adapters.in.controller.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoRequest {
 
	@NotNull
	private Long codigoCliente;
	@NotBlank
	private String statusPedido;

	List<PedidoItemRequest> listaItensPedido;
}
