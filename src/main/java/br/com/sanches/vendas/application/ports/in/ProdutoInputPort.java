package br.com.sanches.vendas.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.ProdutoDTO;

public interface ProdutoInputPort {
 
	ProdutoDTO insert(ProdutoDTO produto);
	
	void delete(Long codigoProduto) throws Exception;
	
	ProdutoDTO update(ProdutoDTO produto) throws Exception;
	
	ProdutoDTO findById(Long codigoProduto) throws Exception;
	
	Page<ProdutoDTO> findAll(Pageable pageable);
}
