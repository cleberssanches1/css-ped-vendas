package br.com.sanches.vendas.application.core.usecase.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.PedidoDTO;
import br.com.sanches.vendas.application.ports.in.PedidoInputPort;
import br.com.sanches.vendas.application.ports.out.PedidoOutPutPort;

public class PedidoUseCase implements PedidoInputPort {
 
    private final PedidoOutPutPort pedidoOutPutPort;
	
	public PedidoUseCase(PedidoOutPutPort pedidoOutPutPort) {
		this.pedidoOutPutPort = pedidoOutPutPort;
	}
	
	@Override
	public PedidoDTO insert(PedidoDTO cliente) {
		return pedidoOutPutPort.insert(cliente);
	}

	@Override
	public void delete(Long codigoPedido) throws Exception {
		pedidoOutPutPort.delete(codigoPedido);		
	}

	@Override
	public PedidoDTO update(PedidoDTO codigoPedido) throws Exception {
		return pedidoOutPutPort.update(codigoPedido);
	}

	@Override
	public PedidoDTO findById(Long codigoPedido) throws Exception {
		return pedidoOutPutPort.findById(codigoPedido);
	}

	@Override
	public Page<PedidoDTO> findAll(Pageable pageable) {
		return pedidoOutPutPort.findAll(pageable);
	}
	
}
