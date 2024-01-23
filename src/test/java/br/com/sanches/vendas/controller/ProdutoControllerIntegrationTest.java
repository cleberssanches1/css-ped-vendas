package br.com.sanches.vendas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.sanches.vendas.adapters.out.repository.ProdutoRepository;
import br.com.sanches.vendas.adapters.out.repository.entity.ProdutoEntity;
 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class ProdutoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	static final MySQLContainer MY_SQL_CONTAINER;

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	private ProdutoRepository produtoRepository;

	static {
		MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
		MY_SQL_CONTAINER.start();
	}

	@DynamicPropertySource
	static void configureTestProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
		registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
		registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
	}

	@BeforeEach
	public void beforeEach() {
		ProdutoEntity produtoEntity = ProdutoEntity.builder().codigoProduto(1L).descricaoProduto("teste")
				.valorProduto(10D).build();

		produtoRepository.save(produtoEntity);
	}

	@AfterEach
	public void afterEach() {
		produtoRepository.deleteAll();
	}

	@Test
	void testInsertProduto() throws Exception {

		mockMvc.perform(post("/api/v1/produto").content("{\"descricao\": \"Produto Teste\", \"valor\": 10.0}")
				.contentType("application/json")).andExpect(status().isOk());
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		public MySQLContainer<?> mySQLContainer() {
			MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest").withDatabaseName("vendas")
					.withUsername("user").withPassword("user");
			mySQLContainer.start();
			return mySQLContainer;
		}
	}
}
