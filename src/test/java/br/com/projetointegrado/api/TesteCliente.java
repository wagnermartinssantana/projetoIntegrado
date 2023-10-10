package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Cliente;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteCliente {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private Cliente exemploCliente;

    @BeforeEach
    void setUp() {
        exemploCliente = new Cliente(999L, "Exemplo Cliente", "1234567890", "exemplo@email.com");
    }

    @Test
    void listarClientesComSucesso() throws Exception {
        mockMvc.perform(get("/cliente/listarTodos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientePorIdComSucesso() throws Exception {
        mockMvc.perform(get("/cliente/obter/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientePorIdClienteNaoEncontrado() throws Exception {
        Long id = 1L; 
        mockMvc.perform(get("/cliente/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void adicionarClienteComSucesso() throws Exception {
        mockMvc.perform(post("/cliente/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exemploCliente)))
                .andExpect(status().isCreated());
    }

    @Test
    void adicionarClienteComRequisicaoInvalida() throws Exception {
        mockMvc.perform(post("/cliente/novo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarClienteComSucesso() throws Exception {
        mockMvc.perform(put("/cliente/atualizar/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exemploCliente)))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarClienteClienteNaoEncontrado() throws Exception {
        Long id = 1L;
        mockMvc.perform(put("/cliente/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exemploCliente)))
                .andExpect(status().isNotFound());
    }

    @Test
    void removerClienteComSucesso() throws Exception {
        Long id = exemploCliente.getId();
        mockMvc.perform(delete("/cliente/remover/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}