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

import br.com.sanches.vendas.adapters.in.controller.mapper.ProdutoMapper;
import br.com.sanches.vendas.adapters.in.controller.request.ProdutoInsertRequest;
import br.com.sanches.vendas.adapters.in.controller.request.ProdutoRequest;
import br.com.sanches.vendas.adapters.in.controller.response.ProdutoResponse;
import br.com.sanches.vendas.application.core.domain.ProdutoDTO;
import br.com.sanches.vendas.application.ports.in.ProdutoInputPort;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoInputPort produtoInputPort;
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> insert(@Required @Valid @RequestBody ProdutoInsertRequest request){
		
		ProdutoDTO produto = ProdutoMapper.fromProdutoRequestToProdutoDTO(request);	  
		return ResponseEntity.ok().body(ProdutoMapper.fromProdutoDtoToProdutResponse(produtoInputPort.insert(produto)));	 
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> findById(@Required @PathVariable final String id) throws NumberFormatException, Exception{		 
		return ResponseEntity.ok().body(ProdutoMapper.fromProdutoDtoToProdutResponse(produtoInputPort.findById(Long.valueOf(id))));	
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> update(@Required @Valid @RequestBody ProdutoRequest request, @Required @PathVariable final String id) throws Exception{
		
		ProdutoDTO produto = ProdutoMapper.fromProdutoRequestToProdutoDTO(request);	  
		produto.setCodigoProduto(Long.valueOf(id));
		
		return ResponseEntity.ok().body(ProdutoMapper.fromProdutoDtoToProdutResponse(produtoInputPort.update(produto)));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Required @PathVariable final String id) throws NumberFormatException, Exception{
		produtoInputPort.delete(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all")
    public ResponseEntity<Page<ProdutoResponse>> findAllProdutos(Pageable pageable) {		
		return ResponseEntity.ok().body(produtoInputPort.findAll(pageable).map(ProdutoMapper::fromProdutoDtoToProdutResponse));	
    } 

}
