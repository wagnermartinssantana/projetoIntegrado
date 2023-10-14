package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Time;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.HorarioTrabalho;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteHorarioTrabalho {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHorarioTrabalhoByIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/horarios-trabalho/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getHorarioTrabalhoPorIdHorarioTrabalhoNaoEncontrado() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/horarios-trabalho/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getHorariosTrabalhoByFuncionarioComSucesso() throws Exception {
        Long funcionarioId = 3L;
        mockMvc.perform(get("/horarios-trabalho/funcionario/{funcionarioId}", funcionarioId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createHorarioTrabalhoComSucesso() throws Exception {
        HorarioTrabalho novoHorarioTrabalho = new HorarioTrabalho(
            (long) 0,
            null,
            "Segunda-feira",
            Time.valueOf("08:00:00"),
            Time.valueOf("17:00:00")
        );

        mockMvc.perform(post("/horarios-trabalho")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoHorarioTrabalho)))
                .andExpect(status().isCreated());
    }

    @Test
    void createHorarioTrabalhoComRequisicaoInvalida() throws Exception {
        HorarioTrabalho horarioTrabalhoInvalido = new HorarioTrabalho();
        mockMvc.perform(post("/horarios-trabalho")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horarioTrabalhoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateHorarioTrabalhoComSucesso() throws Exception {
        Long id = 3L;
        HorarioTrabalho horarioTrabalhoAtualizado = new HorarioTrabalho(
            id,
            null,
            "Terça-feira",
            Time.valueOf("09:00:00"),
            Time.valueOf("18:00:00") 
        );

        mockMvc.perform(put("/horarios-trabalho/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horarioTrabalhoAtualizado)))
                .andExpect(status().isOk());
    }

    @Test
    void updateHorarioTrabalhoHorarioTrabalhoNaoEncontrado() throws Exception {
        Long id = 12345L;
        HorarioTrabalho horarioTrabalhoAtualizado = new HorarioTrabalho(
            id,
            null,
            "Quarta-feira",
            Time.valueOf("10:00:00"),
            Time.valueOf("19:00:00")
        );

        mockMvc.perform(put("/horarios-trabalho/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horarioTrabalhoAtualizado)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteHorarioTrabalhoComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("/horarios-trabalho/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}