package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Agendamento;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteAgendamento {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private Agendamento exemploAgendamento;

    @Test
    void confirmarAgendamentoComSucesso() throws Exception {
        mockMvc.perform(post("/agendamentos/confirmar/{id}", exemploAgendamento.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void confirmarAgendamentoAgendamentoNaoEncontrado() throws Exception {
        Long id = 12345L; 
        mockMvc.perform(post("/agendamentos/confirmar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void reagendarAgendamentoComSucesso() throws Exception {
        mockMvc.perform(put("/agendamentos/reagendar/{id}", exemploAgendamento.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exemploAgendamento)))
                .andExpect(status().isOk());
    }

    @Test
    void reagendarAgendamentoAgendamentoNaoEncontrado() throws Exception {
        Long id = 12345L; 
        mockMvc.perform(put("/agendamentos/reagendar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exemploAgendamento)))
                .andExpect(status().isNotFound());
    }

    @Test
    void cancelarAgendamentoComSucesso() throws Exception {
        mockMvc.perform(delete("/agendamentos/cancelar/{id}", exemploAgendamento.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}