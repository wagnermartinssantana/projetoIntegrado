package br.com.projetointegrado.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.model.entity.Servico;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TesteServico {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void adicionarServicoComSucesso() throws Exception {
    	Servico novoServico = new Servico(
    			3L, "Novo Serviço",
    			"Descrição do Novo Serviço",
    			60,
    			100.0, null
    			);
    	
    	mockMvc.perform(post("/servicos")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(novoServico)))
    	.andExpect(status().isCreated());
    }

    @Test
    void listarServicosComSucesso() throws Exception {
        mockMvc.perform(get("/servicos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    void getServicoPorIdComSucesso() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("/servicos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getServicoPorIdServicoNaoEncontrado() throws Exception {
        Long id = 12345L;
        mockMvc.perform(get("/servicos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


//    @Test
//    void adicionarServicoComRequisicaoInvalida() throws Exception {
//        Servico servicoInvalido = new Servico();
//        mockMvc.perform(post("/servicos")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(servicoInvalido)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void atualizarServicoComSucesso() throws Exception {
//        Long id = 3L;
//
//        Servico servicoAtualizado = new Servico(
//        		            3L, "Novo Serviço",
//        		            "Descrição do Novo Serviço",
//        		            60,
//        		            100.0, null
//        		        );
//
//
//        mockMvc.perform(put("/servicos/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(servicoAtualizado)))
//                .andExpect(status().isOk());
//    }

    @Test
    void atualizarServicoServicoNaoEncontrado() throws Exception {
        Long id = 12345L;

        Servico servicoAtualizado = new Servico(
	            3L, "Novo Serviço",
	            "Descrição do Novo Serviço",
	            60,
	            100.0, null
	        );

        mockMvc.perform(put("/servicos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoAtualizado)))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void removerServicoComSucesso() throws Exception {
//        Long id = 3L;
//        mockMvc.perform(delete("/servicos/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
}