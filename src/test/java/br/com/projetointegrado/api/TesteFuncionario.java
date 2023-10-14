package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Funcionario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteFuncionario {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarFuncionariosComSucesso() throws Exception {
        mockMvc.perform(get("/funcionarios/listarTodos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obterFuncionarioPorIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/funcionarios/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obterFuncionarioPorIdFuncionarioNaoEncontrado() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/funcionarios/obter/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void adicionarFuncionarioComSucesso() throws Exception {
        Funcionario novoFuncionario = new Funcionario(
            null,
            "Novo Funcionário",
            "1234567890",
            "novo@email.com",
            "Rua Nova",
            123,
            "Complemento",
            "Bairro Novo",
            "Cidade Nova",
            "Estado Novo",
            "Pais Novo",
            "Cargo",
            5000.00
        );

        mockMvc.perform(post("/funcionarios/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoFuncionario)))
                .andExpect(status().isCreated());
    }

    @Test
    void adicionarFuncionarioComRequisicaoInvalida() throws Exception {
        Funcionario funcionarioInvalido = new Funcionario();
        mockMvc.perform(post("/funcionarios/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionarioInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarFuncionarioComSucesso() throws Exception {
        Long id = 3L;
        Funcionario funcionarioAtualizado = new Funcionario(
            id,
            "Funcionário Atualizado",
            "9876543210",
            "atualizado@email.com",
            "Rua Atualizada",
            456,
            "Novo Complemento",
            "Novo Bairro",
            "Nova Cidade",
            "Novo Estado",
            "Novo Pais",
            "Novo Cargo",
            6000.00
        );

        mockMvc.perform(put("/funcionarios/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionarioAtualizado)))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarFuncionarioFuncionarioNaoEncontrado() throws Exception {
        Long id = 12345L;
        Funcionario funcionarioAtualizado = new Funcionario(
            id,
            "Funcionário Atualizado",
            "9876543210",
            "atualizado@email.com",
            "Rua Atualizada",
            456,
            "Novo Complemento",
            "Novo Bairro",
            "Nova Cidade",
            "Novo Estado",
            "Novo Pais",
            "Novo Cargo",
            6000.00
        );

        mockMvc.perform(put("/funcionarios/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionarioAtualizado)))
                .andExpect(status().isNotFound());
    }

    @Test
    void removerFuncionarioComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("/funcionarios/remover/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}