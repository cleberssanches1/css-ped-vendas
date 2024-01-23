package br.com.sanches.vendas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sanches.vendas.adapters.in.controller.ProdutoController;
import br.com.sanches.vendas.adapters.in.controller.request.ProdutoInsertRequest;
import br.com.sanches.vendas.application.core.domain.ProdutoDTO;
import br.com.sanches.vendas.application.ports.in.ProdutoInputPort;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProdutoInputPort produtoInputPort;

	@Test
	void testInsertProduto() throws Exception {
		ProdutoInsertRequest request = ProdutoInsertRequest.builder()
				.descricaoProduto("copo")
				.valorProduto(10D).build();

		ProdutoDTO produtoDTO = ProdutoDTO.builder()
				.codigoProduto(1L)
				.descricaoProduto("copo")
				.valorProduto(10D)
				.build();

		when(produtoInputPort.insert(any(ProdutoDTO.class))).thenReturn(produtoDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/produto").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(request))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testFindById() throws Exception {
		Long produtoId = 1L;
		ProdutoDTO produtoDTO = ProdutoDTO.builder()
				.codigoProduto(1L)
				.descricaoProduto("copo")
				.valorProduto(10D)
				.build();

		when(produtoInputPort.findById(eq(produtoId))).thenReturn(produtoDTO);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/produto/{id}", produtoId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}