package br.com.sanches.vendas.adapters.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto", schema = "vendas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_PRODUTO")
	private Long codigoProduto;

	@Column(name = "DS_PRODUTO", nullable = false)
	private String descricaoProduto;

	@Column(name = "VL_PRODUTO")
	private Double valorProduto;

}