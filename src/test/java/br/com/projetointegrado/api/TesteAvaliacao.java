package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Avaliacao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteAvaliacao {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarAvaliacoesComSucesso() throws Exception {
        mockMvc.perform(get("/avaliacoes/listarTodas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obterAvaliacaoPorIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/avaliacoes/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obterAvaliacaoPorIdAvaliacaoNaoEncontrada() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/avaliacoes/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrarAvaliacaoComRequisicaoInvalida() throws Exception {
        Avaliacao avaliacaoInvalida = new Avaliacao();
        mockMvc.perform(post("/avaliacoes/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(avaliacaoInvalida)))
                .andExpect(status().isBadRequest());
    }
}