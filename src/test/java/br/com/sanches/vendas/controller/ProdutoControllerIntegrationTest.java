package br.com.sanches.vendas.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.sanches.vendas.adapters.in.controller.request.ProdutoInsertRequest;
import br.com.sanches.vendas.adapters.out.repository.ProdutoRepository;
import br.com.sanches.vendas.adapters.out.repository.entity.ProdutoEntity;
import br.com.sanches.vendas.config.CustomMySQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerIntegrationTest {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MockMvc mockMvc;

    static {
        System.setProperty("TESTCONTAINERS_RYUK_TIMEOUT", "60000");
    }

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> TestConfig.customMySQLContainer().getJdbcUrl());
        registry.add("spring.datasource.username", () -> TestConfig.customMySQLContainer().getUsername());
        registry.add("spring.datasource.password", () -> TestConfig.customMySQLContainer().getPassword());
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.flyway.enabled", () -> "true");
    }

    @BeforeEach
    public void beforeEach() {
        ProdutoEntity produtoEntity = ProdutoEntity.builder().codigoProduto(1L).descricaoProduto("teste").valorProduto(10D).build();

        produtoRepository.save(produtoEntity);
    }

    @AfterEach
    public void afterEach() {
        produtoRepository.deleteAll();
    }

    @Test
    void testInsertProduto() throws Exception {
        ProdutoInsertRequest request = ProdutoInsertRequest.builder().descricaoProduto("copo").valorProduto(10D).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/produto").contentType(MediaType.APPLICATION_JSON).content(asJsonString(
                        request))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public static CustomMySQLContainer customMySQLContainer() {
            CustomMySQLContainer mySQLContainer = new CustomMySQLContainer();
            mySQLContainer.start();
            return mySQLContainer;
        }
    }

}
