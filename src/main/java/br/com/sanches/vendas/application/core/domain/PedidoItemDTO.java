package br.com.sanches.vendas.application.core.domain;

import java.io.Serializable;
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
public class PedidoItemDTO implements Serializable {
 
	private static final long serialVersionUID = -1247444534172281118L;
	private Long codigoPedido;
	private Long codigoProduto;
	private Integer quantidade;
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoPedido, codigoProduto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItemDTO other = (PedidoItemDTO) obj;
		return Objects.equals(codigoPedido, other.codigoPedido) && Objects.equals(codigoProduto, other.codigoProduto);
	}
  
}
