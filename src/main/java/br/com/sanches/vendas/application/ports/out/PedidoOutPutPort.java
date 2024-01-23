package br.com.sanches.vendas.application.ports.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.ClienteDTO;
import br.com.sanches.vendas.application.core.domain.PedidoDTO;

public interface PedidoOutPutPort {
	PedidoDTO insert(PedidoDTO cliente);

	void delete(Long codigoPedido) throws Exception;

	PedidoDTO update(PedidoDTO pedido) throws Exception;

	PedidoDTO findById(Long codigoPedido) throws Exception;

	Page<PedidoDTO> findAll(Pageable pageable);
}
