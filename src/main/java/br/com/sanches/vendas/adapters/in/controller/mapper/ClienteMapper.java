package br.com.sanches.vendas.adapters.in.controller.mapper;

import br.com.sanches.vendas.adapters.in.controller.request.ClienteRequest;
import br.com.sanches.vendas.adapters.in.controller.request.ClienteRequestInsert;
import br.com.sanches.vendas.adapters.in.controller.response.ClienteResponse;
import br.com.sanches.vendas.adapters.out.repository.entity.ClienteEntity;
import br.com.sanches.vendas.application.core.domain.ClienteDTO;

public class ClienteMapper {

	private ClienteMapper() {
	}

	public static ClienteDTO fromClienteRequestToClienteDTO(ClienteRequest clienteRequest) {

		return ClienteDTO.builder()
				.email(clienteRequest.getEmail())
				.codigoCliente(clienteRequest.getCodigoCliente())
				.nomeCliente(clienteRequest.getNomeCliente())
				.build();
	}

	public static ClienteDTO fromClienteEntityToClienteDTO(ClienteEntity clienteEntity) {

		return ClienteDTO.builder()
				.email(clienteEntity.getEmail())
				.codigoCliente(clienteEntity.getCodigoCliente())
				.nomeCliente(clienteEntity.getNomeCliente())
				.build();
	}

	public static ClienteEntity fromClienteRequestToClienteDTO(ClienteDTO clienteDTO) {

		return ClienteEntity.builder()
				.email(clienteDTO.getEmail())
				.codigoCliente(clienteDTO.getCodigoCliente())
				.nomeCliente(clienteDTO.getNomeCliente())
				.build();
	}

	public static ClienteResponse fromClienteDtoToClienteResponse(ClienteDTO clienteDTO) {

		return ClienteResponse.builder()
				.email(clienteDTO.getEmail())
				.codigoCliente(clienteDTO.getCodigoCliente())
				.nomeCliente(clienteDTO.getNomeCliente())
				.build();
	}

	public static ClienteDTO fromClienteRequestToClienteDTO(ClienteRequestInsert clienteRequestInsert) {

		return ClienteDTO.builder()
				.email(clienteRequestInsert.getEmail())
				.nomeCliente(clienteRequestInsert.getNomeCliente())
				.build();
	}

}
