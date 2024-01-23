package br.com.sanches.vendas.adapters.out.cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.sanches.vendas.adapters.in.controller.mapper.ClienteMapper;
import br.com.sanches.vendas.adapters.out.exception.EntityNotFoundException;
import br.com.sanches.vendas.adapters.out.repository.ClienteRepository;
import br.com.sanches.vendas.adapters.out.repository.entity.ClienteEntity;
import br.com.sanches.vendas.application.core.domain.ClienteDTO;
import br.com.sanches.vendas.application.core.domain.ClientePedidoDTO;
import br.com.sanches.vendas.application.core.domain.PedidoDTO;
import br.com.sanches.vendas.application.core.domain.PedidoItemDTO;
import br.com.sanches.vendas.application.ports.out.ClienteOutPutPort;

@Component
public class ClienteAdapter implements ClienteOutPutPort{

	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ClienteDTO insert(ClienteDTO cliente) {
		return ClienteMapper.fromClienteEntityToClienteDTO(clienteRepository.save(ClienteMapper.fromClienteRequestToClienteDTO(cliente)));
	}

	@Override
	public void delete(Long codigoCliente) throws Exception {
		ClienteEntity existingCliente = clienteRepository.findById(codigoCliente)
				.orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));

		clienteRepository.delete(existingCliente);		
	}

	@Override
	public ClienteDTO update(ClienteDTO cliente) throws Exception {
		ClienteEntity existingCliente = clienteRepository.findById(cliente.getCodigoCliente())
				.orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));

		existingCliente.setEmail(cliente.getEmail());
		existingCliente.setNomeCliente(cliente.getNomeCliente());

		ClienteEntity updatedCliente = clienteRepository.save(existingCliente);

		return ClienteMapper.fromClienteEntityToClienteDTO(updatedCliente);
	}

	@Override
	public ClienteDTO findById(Long codigoCliente) throws Exception {

		Optional<ClienteEntity> optionalCliente = clienteRepository.findById(codigoCliente);
		return optionalCliente.map(ClienteMapper::fromClienteEntityToClienteDTO)
				.orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
	}

	@Override
	public Page<ClienteDTO> findAll(Pageable pageable) {
		Page<ClienteEntity> clientePage = clienteRepository.findAll(pageable);
		return clientePage.map(ClienteMapper::fromClienteEntityToClienteDTO);
	}
 
	@Override
	public List<ClientePedidoDTO> findClientePedidosById(Long codCliente) throws Exception {

	    List<Object[]> results = clienteRepository.findClientePedidosItens(codCliente);

	    if(results.isEmpty()) {
	    	throw new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO);
	    }
	    
	    Map<Long, ClientePedidoDTO> clienteMap = new HashMap<>();

	    for (Object[] result : results) {
	        Long codigoCliente = Long.valueOf(result[0].toString());
	        String nomeCliente = (String) result[1];
	        String email = (String) result[2];

	        Long codigoPedido = Long.valueOf(result[3].toString());
	        String statusPedido = result[4].toString();
	        Long codigoProduto = Long.valueOf(result[5].toString());
	        Integer quantidade = Integer.valueOf(result[6].toString());

	        PedidoItemDTO pedidoItemDTO = PedidoItemDTO.builder()
	                .codigoPedido(codigoPedido)
	                .codigoProduto(codigoProduto)
	                .quantidade(quantidade)
	                .build();

	        ClientePedidoDTO clientePedidoDTO = clienteMap.computeIfAbsent(codigoCliente, key ->
	                ClientePedidoDTO.builder()
	                        .codigoCliente(codigoCliente)
	                        .nomeCliente(nomeCliente)
	                        .email(email)
	                        .pedidoDTO(new ArrayList<>())
	                        .build()
	        );

	        Optional<PedidoDTO> existingPedido = clientePedidoDTO.getPedidoDTO()
	                .stream()
	                .filter(p -> Objects.equals(p.getCodigoPedido(), codigoPedido))
	                .findFirst();

	        existingPedido.ifPresentOrElse(
	                p -> {
	                    if (p.getListaItensPedido().stream().noneMatch(item -> Objects.equals(item.getCodigoProduto(), codigoProduto))) {
	                        p.getListaItensPedido().add(pedidoItemDTO);
	                    }
	                },
	                () -> clientePedidoDTO.getPedidoDTO().add(
	                        PedidoDTO.builder()
	                                .codigoCliente(codigoCliente)
	                                .codigoPedido(codigoPedido)
	                                .statusPedido(statusPedido)
	                                .listaItensPedido(Collections.singletonList(pedidoItemDTO))
	                                .build()
	                )
	        );
	    }

	    return new ArrayList<>(clienteMap.values());
	}
}
