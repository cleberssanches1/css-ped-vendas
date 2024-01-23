package br.com.sanches.vendas.adapters.out.repository.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemPK implements Serializable {

	private static final long serialVersionUID = -5764911378376492722L;

	@ManyToOne
	@JoinColumn(name = "cd_pedido")
	private PedidoEntity pedido;

	@ManyToOne
	@JoinColumn(name = "cd_produto")
	private ProdutoEntity produto;

}