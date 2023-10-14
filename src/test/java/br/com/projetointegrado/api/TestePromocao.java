package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Promocao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestePromocao {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarPromocoesComSucesso() throws Exception {
        mockMvc.perform(get("/promocoes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPromocaoPorIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/promocoes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPromocaoPorIdPromocaoNaoEncontrada() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/promocoes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarPromocaoComSucesso() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio = sdf.parse("2023-11-01");
        Date dataFim = sdf.parse("2023-11-15");

        Promocao novaPromocao = new Promocao(
            (long) 0, 
            null, 
            dataInicio,
            dataFim,
            "Descrição da Promoção",
            0.1
        );

        mockMvc.perform(post("/promocoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPromocao)))
                .andExpect(status().isCreated());
    }

    @Test
    void criarPromocaoComRequisicaoInvalida() throws Exception {
        Promocao promocaoInvalida = new Promocao();
        mockMvc.perform(post("/promocoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(promocaoInvalida)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarPromocaoComSucesso() throws Exception {
        Long id = 3L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio = sdf.parse("2023-12-01");
        Date dataFim = sdf.parse("2023-12-15");

        Promocao promocaoAtualizada = new Promocao(
            id,
            null,
            dataInicio,
            dataFim,
            "Descrição da Promoção Atualizada",
            0.2
        );

        mockMvc.perform(put("/promocoes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(promocaoAtualizada)))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarPromocaoPromocaoNaoEncontrada() throws Exception {
        Long id = 12345L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio = sdf.parse("2023-12-01");
        Date dataFim = sdf.parse("2023-12-15");

        Promocao promocaoAtualizada = new Promocao(
            id,
            null,
            dataInicio,
            dataFim,
            "Descrição da Promoção Atualizada",
            0.2
        );

        mockMvc.perform(put("/promocoes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(promocaoAtualizada)))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void removerPromocaoComSucesso() throws Exception {
//        Long id = 3L;
//        mockMvc.perform(delete("/promocoes/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
}