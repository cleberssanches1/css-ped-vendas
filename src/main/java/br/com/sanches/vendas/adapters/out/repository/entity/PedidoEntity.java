package br.com.sanches.vendas.adapters.out.repository.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido", schema = "vendas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {
	
	private static final String SAO_PAULO_TIMEZONE_ID ="America/Sao_Paulo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PEDIDO")
    private Long codigoPedido;

    @Column(name = "CD_CLIENTE", nullable = false)
    private Long codigoCliente;

    @Column(name = "ST_PEDIDO")
    private String statusPedido;

    @Column(name = "TS_PEDIDO")
    private LocalDateTime timestampPedido;

    @ManyToOne
    @JoinColumn(name = "CD_CLIENTE", referencedColumnName = "CD_CLIENTE", insertable = false, updatable = false)
    private ClienteEntity cliente;
 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itensPedido = new ArrayList<>();
    
    @PrePersist
	public void prePersist() {
		this.timestampPedido = LocalDateTime.now(ZoneId.of(TimeZone.getTimeZone(SAO_PAULO_TIMEZONE_ID).getID()));
	}

	@PreUpdate
	public void preUpdate() {
		this.timestampPedido = LocalDateTime.now(ZoneId.of(TimeZone.getTimeZone(SAO_PAULO_TIMEZONE_ID).getID()));
	}
    
}