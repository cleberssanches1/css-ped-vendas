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
public class ClientePedidoDTO implements Serializable {
 
	private static final long serialVersionUID = -3109996287490552455L;

	private Long codigoCliente;

	private String nomeCliente;

	private String email;
	
	private List<PedidoDTO> pedidoDTO;

	@Override
	public int hashCode() {
		return Objects.hash(codigoCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientePedidoDTO other = (ClientePedidoDTO) obj;
		return Objects.equals(codigoCliente, other.codigoCliente);
	}
	
	
}
