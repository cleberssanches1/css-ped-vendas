package br.com.sanches.vendas.application.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO implements Serializable {
 
	private static final long serialVersionUID = 2935812837844439356L;

	private Long codigoPedido;

	private Long codigoCliente;

	private String statusPedido;
	 
	List<PedidoItemDTO> listaItensPedido;

	@Override
	public int hashCode() {
		return Objects.hash(codigoCliente, codigoPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDTO other = (PedidoDTO) obj;
		return Objects.equals(codigoCliente, other.codigoCliente) && Objects.equals(codigoPedido, other.codigoPedido);
	}
	
	
}
