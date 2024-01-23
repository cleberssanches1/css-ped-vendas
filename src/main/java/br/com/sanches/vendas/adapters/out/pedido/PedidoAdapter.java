package br.com.sanches.vendas.adapters.out.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.sanches.vendas.adapters.in.controller.mapper.PedidoMapper;
import br.com.sanches.vendas.adapters.out.exception.EntityNotFoundException;
import br.com.sanches.vendas.adapters.out.repository.PedidoItemRepository;
import br.com.sanches.vendas.adapters.out.repository.PedidoRepository;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoEntity;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoItemEntity;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoItemPK;
import br.com.sanches.vendas.adapters.out.repository.entity.ProdutoEntity;
import br.com.sanches.vendas.application.core.domain.PedidoDTO;
import br.com.sanches.vendas.application.core.domain.PedidoItemDTO;
import br.com.sanches.vendas.application.ports.out.PedidoOutPutPort;

@Component
public class PedidoAdapter implements PedidoOutPutPort {

	private static final String PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado";

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Override
	public PedidoDTO insert(PedidoDTO pedido) {
		
		PedidoEntity entity = pedidoRepository.save(PedidoMapper.fromPedidoDTOToPedidoEntity(pedido));
		
		List<PedidoItemEntity> listaPedidoItemEntity = new ArrayList<>();
				
		salvarItensPedido(pedido, entity, listaPedidoItemEntity);
		
		entity.getItensPedido().addAll(listaPedidoItemEntity);
	 
		return PedidoMapper.fromPedidoEntityToPedidoDTO(entity);
	}
 
	@Override
	public void delete(Long codigoPedido) throws Exception {
		PedidoEntity existingPedido = pedidoRepository.findById(codigoPedido)
				.orElseThrow(() -> new EntityNotFoundException(PEDIDO_NAO_ENCONTRADO));

		pedidoRepository.delete(existingPedido);
	}

	@Override
	public PedidoDTO update(PedidoDTO pedido) throws EntityNotFoundException {
		PedidoEntity existingPedido = pedidoRepository.findById(pedido.getCodigoPedido())
				.orElseThrow(() -> new EntityNotFoundException(PEDIDO_NAO_ENCONTRADO));

		existingPedido.setStatusPedido(pedido.getStatusPedido());

		PedidoEntity updatedPedido = pedidoRepository.save(existingPedido);
		
		List<PedidoItemEntity> listaPedidoItemEntity = new ArrayList<>();
		
		salvarItensPedido(pedido, updatedPedido, listaPedidoItemEntity);
		 
		updatedPedido.setItensPedido(pedidoRepository.findById(existingPedido.getCodigoPedido()).get().getItensPedido());

		return PedidoDTO.builder()
				.codigoCliente(updatedPedido.getCodigoCliente())
				.codigoPedido(updatedPedido.getCodigoPedido())
				.statusPedido(updatedPedido.getStatusPedido())
				.listaItensPedido(PedidoMapper.fromListaPedidoEntityToListaPedidoDTO(updatedPedido.getItensPedido(), updatedPedido.getCodigoPedido()))
				.build();
	}

	@Override
	public PedidoDTO findById(Long codigoPedido) throws Exception {

		Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(codigoPedido);

		if (optionalPedido.isEmpty()) {
			throw new EntityNotFoundException(PEDIDO_NAO_ENCONTRADO);
		}

		return PedidoMapper.fromPedidoEntityToPedidoDTO(optionalPedido.get());
	}

	@Override
	public Page<PedidoDTO> findAll(Pageable pageable) {
		Page<PedidoEntity> pedidoPage = pedidoRepository.findAll(pageable);
		return pedidoPage.map(PedidoMapper::fromPedidoEntityToPedidoDTO);
	}
	
	private void salvarItensPedido(PedidoDTO pedido, PedidoEntity entity,
			List<PedidoItemEntity> listaPedidoItemEntity) {
		if(Objects.nonNull(pedido.getListaItensPedido()) && !pedido.getListaItensPedido().isEmpty()) {
			  
			for(PedidoItemDTO item : pedido.getListaItensPedido()) {
				
				ProdutoEntity produto = ProdutoEntity.builder().codigoProduto(item.getCodigoProduto()).build();
				
				PedidoItemPK id = PedidoItemPK.builder()
				.pedido(entity)
				.produto(produto)
				.build() ;
		 
				listaPedidoItemEntity.add(PedidoItemEntity.builder().id(id).quantidade(item.getQuantidade()).build());				
			}
			
			pedidoItemRepository.saveAll(listaPedidoItemEntity);
		}
	}

}
