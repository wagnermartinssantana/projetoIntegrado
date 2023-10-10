package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Produto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteProduto {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarProdutosComSucesso() throws Exception {
        mockMvc.perform(get("/produtos/listarTodos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProdutoByIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/produtos/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProdutoPorIdProdutoNaoEncontrado() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/produtos/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void adicionarProdutoComSucesso() throws Exception {
        Produto novoProduto = new Produto(
            "Nome do Produto",
            "Descrição do Produto",
            19.99,
            50
        );

        mockMvc.perform(post("/produtos/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoProduto)))
                .andExpect(status().isCreated());
    }

    @Test
    void adicionarProdutoComRequisicaoInvalida() throws Exception {
        Produto produtoInvalido = new Produto();
        mockMvc.perform(post("/produtos/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarProdutoComSucesso() throws Exception {
        Long id = 3L;
        Produto produtoAtualizado = new Produto(
            "Nome do Produto Atualizado",
            "Descrição do Produto Atualizado",
            29.99,
            75
        );

        mockMvc.perform(put("/produtos/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoAtualizado)))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarProdutoProdutoNaoEncontrado() throws Exception {
        Long id = 12345L;
        Produto produtoAtualizado = new Produto(
            "Nome do Produto Atualizado",
            "Descrição do Produto Atualizado",
            29.99,
            75
        );

        mockMvc.perform(put("/produtos/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoAtualizado)))
                .andExpect(status().isNotFound());
    }

    @Test
    void removerProdutoComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("/produtos/remover/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}