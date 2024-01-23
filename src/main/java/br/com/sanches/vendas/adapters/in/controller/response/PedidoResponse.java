package br.com.sanches.vendas.adapters.in.controller.response;

import java.util.List;

import br.com.sanches.vendas.application.core.domain.PedidoItemDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponse {

	private Long codigoPedido;

	private Long codigoCliente;

	private String statusPedido;

	List<PedidoItemDTO> listaItensPedido;

}
