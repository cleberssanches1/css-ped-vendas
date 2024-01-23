package br.com.sanches.vendas.application.core.usecase.cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.ClienteDTO;
import br.com.sanches.vendas.application.core.domain.ClientePedidoDTO;
import br.com.sanches.vendas.application.ports.in.ClienteInputPort;
import br.com.sanches.vendas.application.ports.out.ClienteOutPutPort;

public class ClienteUseCase implements ClienteInputPort{
 
	private final ClienteOutPutPort clienteOutPutPort;
	
	public ClienteUseCase(ClienteOutPutPort clienteOutPutPort) {
		this.clienteOutPutPort = clienteOutPutPort;
	}
	
	@Override
	public ClienteDTO insert(ClienteDTO cliente) {
		return clienteOutPutPort.insert(cliente);
	}

	@Override
	public void delete(Long codigoCliente) throws Exception {
		clienteOutPutPort.delete(codigoCliente);		
	}

	@Override
	public ClienteDTO update(ClienteDTO cliente) throws Exception {
		return clienteOutPutPort.update(cliente);
	}

	@Override
	public ClienteDTO findById(Long codigocliente) throws Exception {
		return clienteOutPutPort.findById(codigocliente);
	}

	@Override
	public Page<ClienteDTO> findAll(Pageable pageable) {
		return clienteOutPutPort.findAll(pageable);
	}

	@Override
	public List<ClientePedidoDTO> findClientePedidosById(Long codigoCliente) throws Exception  {
		return clienteOutPutPort.findClientePedidosById(codigoCliente);
	}
}
