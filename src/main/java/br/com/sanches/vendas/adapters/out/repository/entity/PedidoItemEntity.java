package br.com.sanches.vendas.adapters.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidoitem", schema = "vendas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemEntity {

	@EmbeddedId
	private PedidoItemPK id;
	
	@Column(name = "QT_PRODUTO")
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "CD_PEDIDO", insertable = false, updatable = false)
	private PedidoEntity pedido;

	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private ProdutoEntity produto;
 
}
