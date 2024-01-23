package br.com.sanches.vendas.adapters.out.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.sanches.vendas.adapters.in.controller.mapper.ProdutoMapper;
import br.com.sanches.vendas.adapters.out.exception.EntityNotFoundException;
import br.com.sanches.vendas.adapters.out.repository.ProdutoRepository;
import br.com.sanches.vendas.adapters.out.repository.entity.ProdutoEntity;
import br.com.sanches.vendas.application.core.domain.ProdutoDTO;
import br.com.sanches.vendas.application.ports.out.ProdutoOutPutPort;

@Component
public class ProdutoAdapter implements ProdutoOutPutPort {

	private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public ProdutoDTO insert(ProdutoDTO produto) {		
		return ProdutoMapper.fromProdutoEntityToProdutoDTO(produtoRepository.save(ProdutoMapper.fromProdutoRequestToProdutoDTO(produto)));
	}

	@Override
	public void delete(Long codigoProduto) throws Exception {
		ProdutoEntity existingProduto = produtoRepository.findById(codigoProduto)
				.orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));

		produtoRepository.delete(existingProduto);
	}

	@Override
	public ProdutoDTO update(ProdutoDTO produto) throws Exception {
		ProdutoEntity existingProduto = produtoRepository.findById(produto.getCodigoProduto())
				.orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));

		existingProduto.setDescricaoProduto(produto.getDescricaoProduto());
		existingProduto.setValorProduto(produto.getValorProduto());

		ProdutoEntity updatedProduto = produtoRepository.save(existingProduto);

		return ProdutoMapper.fromProdutoEntityToProdutoDTO(updatedProduto);
	}

	@Override
	public ProdutoDTO findById(Long codigoProduto) throws Exception {

		Optional<ProdutoEntity> optionalProduto = produtoRepository.findById(codigoProduto);
		return optionalProduto.map(ProdutoMapper::fromProdutoEntityToProdutoDTO)
				.orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));
	}

	@Override
	public Page<ProdutoDTO> findAll(Pageable pageable) {	
		Page<ProdutoEntity> produtoPage = produtoRepository.findAll(pageable);
		return produtoPage.map(ProdutoMapper::fromProdutoEntityToProdutoDTO);
	}

}
