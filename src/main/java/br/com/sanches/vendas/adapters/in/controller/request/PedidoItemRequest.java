package br.com.sanches.vendas.adapters.in.controller.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoItemRequest implements Serializable {
 
	private static final long serialVersionUID = 6614303611357309921L;
	private Long codigoProduto;
	private Integer quantidade;
}
