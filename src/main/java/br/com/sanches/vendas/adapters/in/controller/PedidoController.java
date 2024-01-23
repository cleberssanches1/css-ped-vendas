package br.com.sanches.vendas.adapters.in.controller;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sanches.vendas.adapters.in.controller.mapper.PedidoMapper;
import br.com.sanches.vendas.adapters.in.controller.request.PedidoRequest;
import br.com.sanches.vendas.adapters.in.controller.response.PedidoResponse;
import br.com.sanches.vendas.application.core.domain.PedidoDTO;
import br.com.sanches.vendas.application.ports.in.PedidoInputPort;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoInputPort pedidoInputPort;
	
	@PostMapping
	public ResponseEntity<PedidoResponse> insert(@Required @Valid @RequestBody PedidoRequest request){
		
		PedidoDTO pedido = PedidoMapper.fromPedidoRequestToPedidoDTO(request);	  
	 
		return ResponseEntity.ok().body(PedidoMapper.fromPedidoDtoToPedidoResponse(pedidoInputPort.insert(pedido)));	 
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponse> findById(@Required @PathVariable final String id) throws NumberFormatException, Exception{		 
		return ResponseEntity.ok().body(PedidoMapper.fromPedidoDtoToPedidoResponse(pedidoInputPort.findById(Long.valueOf(id))));	
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<PedidoResponse> update(@Required @Valid @RequestBody PedidoRequest request, @Required @PathVariable final String id) throws Exception{
		
		PedidoDTO pedido = PedidoMapper.fromPedidoRequestToPedidoDTO(request);	  
		pedido.setCodigoPedido(Long.valueOf(id));
		
		return ResponseEntity.ok().body(PedidoMapper.fromPedidoDtoToPedidoResponse(pedidoInputPort.update(pedido)));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Required @PathVariable final String id) throws NumberFormatException, Exception{
		pedidoInputPort.delete(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all")
    public ResponseEntity<Page<PedidoResponse>> findAllPedidos(Pageable pageable) {		
		return ResponseEntity.ok().body(pedidoInputPort.findAll(pageable).map(PedidoMapper::fromPedidoDtoToPedidoResponse));	
    } 
	
}
