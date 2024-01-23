package br.com.sanches.vendas.application.ports.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.ClienteDTO;
import br.com.sanches.vendas.application.core.domain.ClientePedidoDTO;

public interface ClienteOutPutPort {
	ClienteDTO insert(ClienteDTO cliente);

	void delete(Long codigoCliente) throws Exception;

	ClienteDTO update(ClienteDTO cliente) throws Exception;

	ClienteDTO findById(Long codigoCliente) throws Exception;

	Page<ClienteDTO> findAll(Pageable pageable);
	
	List<ClientePedidoDTO> findClientePedidosById(Long codigoCliente) throws Exception ;
}
