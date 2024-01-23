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
@Table(name = "cliente", schema = "vendas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CLIENTE")
    private Long codigoCliente;

    @Column(name = "NM_CLIENTE", nullable = false)
    private String nomeCliente;

    @Column(name = "DS_EMAIL")
    private String email;
 
}