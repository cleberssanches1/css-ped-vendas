package br.com.sanches.vendas.adapters.in.controller;

import java.util.List;

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

import br.com.sanches.vendas.adapters.in.controller.mapper.ClienteMapper;
import br.com.sanches.vendas.adapters.in.controller.request.ClienteRequestInsert;
import br.com.sanches.vendas.adapters.in.controller.request.ClienteRequest;
import br.com.sanches.vendas.adapters.in.controller.response.ClienteResponse;
import br.com.sanches.vendas.application.core.domain.ClienteDTO;
import br.com.sanches.vendas.application.core.domain.ClientePedidoDTO;
import br.com.sanches.vendas.application.ports.in.ClienteInputPort;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

	@Autowired
	private ClienteInputPort clienteInputPort;
	
	@PostMapping
	public ResponseEntity<ClienteResponse> insert(@Required @Valid @RequestBody ClienteRequestInsert request){
		
		ClienteDTO produto = ClienteMapper.fromClienteRequestToClienteDTO(request);	  
		return ResponseEntity.ok().body(ClienteMapper.fromClienteDtoToClienteResponse(clienteInputPort.insert(produto)));	 
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> findById(@Required @PathVariable final String id) throws NumberFormatException, Exception{		 
		return ResponseEntity.ok().body(ClienteMapper.fromClienteDtoToClienteResponse(clienteInputPort.findById(Long.valueOf(id))));	
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> update(@Required @Valid @RequestBody ClienteRequest request, @Required @PathVariable final String id) throws Exception{
		
		ClienteDTO produto = ClienteMapper.fromClienteRequestToClienteDTO(request);	  
		produto.setCodigoCliente(Long.valueOf(id));
		
		return ResponseEntity.ok().body(ClienteMapper.fromClienteDtoToClienteResponse(clienteInputPort.update(produto)));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Required @PathVariable final String id) throws NumberFormatException, Exception{
		clienteInputPort.delete(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all")
    public ResponseEntity<Page<ClienteResponse>> findAllClientes(Pageable pageable) {		
		return ResponseEntity.ok().body(clienteInputPort.findAll(pageable).map(ClienteMapper::fromClienteDtoToClienteResponse));	
    } 
	
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<List<ClientePedidoDTO>> findClientePedidosById(@Required @PathVariable final String id) throws NumberFormatException, Exception{		 
		return ResponseEntity.ok().body(clienteInputPort.findClientePedidosById(Long.valueOf(id)));	
	}
		
}
