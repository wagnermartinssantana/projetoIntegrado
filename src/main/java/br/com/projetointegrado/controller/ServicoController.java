package br.com.projetointegrado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Servico;
import br.com.projetointegrado.model.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Servicos", description = "Endpoints da API do Projeto Integrado para os Serviços")
public class ServicoController {

	@Autowired
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(summary = "Lista todos os serviços", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Serviços listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar serviços"),
    })
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();
        return ResponseEntity.ok(servicos);
    }
    
    @Operation(summary = "Obtém um serviço por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Serviço obtido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Serviço não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter serviço"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Servico>> getServicoPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.getServicoPorId(id);
        if (servico.isPresent()) {
            return ResponseEntity.ok(servico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um novo serviço", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar serviço"),
    })
    @PostMapping
    public ResponseEntity<Servico> adicionarServico(@RequestBody Servico servico) {
        Servico novoServico = servicoService.adicionarServico(servico);
        return ResponseEntity.status(201).body(novoServico);
    }

    @Operation(summary = "Atualiza um serviço por ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Serviço não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar serviço"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id, @RequestBody Servico servico) {
        Servico servicoAtualizado = servicoService.atualizarServico(id, servico);
        if (servicoAtualizado != null) {
            return ResponseEntity.ok(servicoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um serviço por ID", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Serviço removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Serviço não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao remover serviço"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerServico(@PathVariable Long id) {
        servicoService.removerServico(id);
        return ResponseEntity.noContent().build();
    }
}