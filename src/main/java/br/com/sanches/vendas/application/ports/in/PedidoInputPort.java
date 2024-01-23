package br.com.sanches.vendas.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.PedidoDTO;

public interface PedidoInputPort {
 
	PedidoDTO insert(PedidoDTO produto);
	
	void delete(Long codigoPedido) throws Exception;
	
	PedidoDTO update(PedidoDTO pedido) throws Exception;
	
	PedidoDTO findById(Long codigoPedido) throws Exception;
	
	Page<PedidoDTO> findAll(Pageable pageable);
}
