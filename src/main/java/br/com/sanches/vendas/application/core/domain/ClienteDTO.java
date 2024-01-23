package br.com.sanches.vendas.application.core.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 4349093103584364779L;

	private Long codigoCliente;

	private String nomeCliente;

	private String email;
}
