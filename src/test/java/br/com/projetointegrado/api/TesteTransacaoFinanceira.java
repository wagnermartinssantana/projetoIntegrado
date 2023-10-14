package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.TransacaoFinanceira;
import br.com.projetointegrado.model.enums.TipoTransacaoEnum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteTransacaoFinanceira {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarTransacoesFinanceirasComSucesso() throws Exception {
        mockMvc.perform(get("/transacoes-financeiras/listar-todas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTransacaoFinanceiraPorIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/transacoes-financeiras/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTransacaoFinanceiraPorIdTransacaoNaoEncontrada() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/transacoes-financeiras/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrarTransacaoFinanceiraComSucesso() throws Exception {
        TransacaoFinanceira novaTransacao = new TransacaoFinanceira(
            TipoTransacaoEnum.RECEITA,
            100.0,
            new Date()
        );

        mockMvc.perform(post("/transacoes-financeiras/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaTransacao)))
                .andExpect(status().isCreated());
    }

    @Test
    void registrarTransacaoFinanceiraComRequisicaoInvalida() throws Exception {
        TransacaoFinanceira transacaoInvalida = new TransacaoFinanceira();
        mockMvc.perform(post("/transacoes-financeiras/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacaoInvalida)))
                .andExpect(status().isBadRequest());
    }
}