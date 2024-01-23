package br.com.sanches.vendas.adapters.in.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.sanches.vendas.adapters.in.controller.request.PedidoItemRequest;
import br.com.sanches.vendas.adapters.in.controller.request.PedidoRequest;
import br.com.sanches.vendas.adapters.in.controller.response.PedidoResponse;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoEntity;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoItemEntity;
import br.com.sanches.vendas.application.core.domain.PedidoDTO;
import br.com.sanches.vendas.application.core.domain.PedidoItemDTO;

public class PedidoMapper {

	private PedidoMapper() {
	}
	
	public static PedidoDTO fromPedidoRequestToPedidoDTO(PedidoRequest pedidoRequest) {

		return PedidoDTO.builder()
		.codigoCliente(pedidoRequest.getCodigoCliente())
		.statusPedido(pedidoRequest.getStatusPedido())
		.listaItensPedido(fromListaPedidoRequestToListaPedidoDTO(pedidoRequest.getListaItensPedido())).build();	 
	}
	
	public static PedidoEntity fromPedidoDTOToPedidoEntity(PedidoDTO pedidoDTO) {

		return PedidoEntity.builder()
				.codigoCliente(pedidoDTO.getCodigoCliente())
				.codigoPedido(pedidoDTO.getCodigoPedido())
				.statusPedido(pedidoDTO.getStatusPedido())
				.itensPedido(new ArrayList<>())
				.build();				 
	}
	 
	public static PedidoDTO fromPedidoEntityToPedidoDTO(PedidoEntity pedidoEntity) {

		return PedidoDTO.builder()
				.codigoCliente(pedidoEntity.getCodigoCliente())
				.codigoPedido(pedidoEntity.getCodigoPedido())
				.statusPedido(pedidoEntity.getStatusPedido())
				.listaItensPedido(fromListaPedidoEntityToListaPedidoDTO(pedidoEntity.getItensPedido(), pedidoEntity.getCodigoPedido()))
				.build();				 
	}
	
	public static PedidoResponse fromPedidoDtoToPedidoResponse(PedidoDTO pedidoDTO) {

		return PedidoResponse.builder()
				.codigoCliente(pedidoDTO.getCodigoCliente())
				.codigoPedido(pedidoDTO.getCodigoPedido())
				.statusPedido(pedidoDTO.getStatusPedido())
				.listaItensPedido(pedidoDTO.getListaItensPedido())
				.build();				 
	}
 
	public static List<PedidoItemDTO> fromListaPedidoRequestToListaPedidoDTO(List<PedidoItemRequest> lista){
		
		List<PedidoItemDTO> pedidoItemDTOList = new ArrayList<>();
		
		for(PedidoItemRequest item: lista) {
			
			PedidoItemDTO itemDTO = PedidoItemDTO.builder()
					.codigoProduto(item.getCodigoProduto())
					.quantidade(item.getQuantidade())
					.build();
			
			pedidoItemDTOList.add(itemDTO);
		}
		 
		return pedidoItemDTOList;
	}
	
	
    public static List<PedidoItemDTO> fromListaPedidoEntityToListaPedidoDTO(List<PedidoItemEntity> lista, Long codigoPedido){
		
		List<PedidoItemDTO> pedidoItemDTOList = new ArrayList<>();
		
		for(PedidoItemEntity item: lista) {
			
			PedidoItemDTO itemDTO = PedidoItemDTO.builder()
					.codigoPedido(codigoPedido)
					.codigoProduto(item.getId().getProduto().getCodigoProduto())
					.quantidade(item.getQuantidade()).build();
			
			pedidoItemDTOList.add(itemDTO);
		}
		 
		return pedidoItemDTOList;
	}
    
    
   
	
}
