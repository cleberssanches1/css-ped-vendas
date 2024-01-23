package br.com.sanches.vendas.application.core.usecase.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sanches.vendas.application.core.domain.ProdutoDTO;
import br.com.sanches.vendas.application.ports.in.ProdutoInputPort;
import br.com.sanches.vendas.application.ports.out.ProdutoOutPutPort;

public class ProdutoUseCase implements ProdutoInputPort {

	private final ProdutoOutPutPort produtoOutPutPort;

	public ProdutoUseCase(ProdutoOutPutPort produtoOutPutPort) {
		this.produtoOutPutPort = produtoOutPutPort;
	}

	@Override
	public ProdutoDTO insert(ProdutoDTO produto) {		 
		return produtoOutPutPort.insert(produto);
	}

	@Override
	public void delete(Long codigoProduto) throws Exception {
		produtoOutPutPort.delete(codigoProduto);
	}

	@Override
	public ProdutoDTO update(ProdutoDTO produto) throws Exception {
		return produtoOutPutPort.update(produto);
	}

	@Override
	public ProdutoDTO findById(Long codigoProduto) throws Exception {
		return produtoOutPutPort.findById(codigoProduto);
	}

	@Override
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		return produtoOutPutPort.findAll(pageable);
	}

}
