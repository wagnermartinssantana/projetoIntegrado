package br.com.projetointegrado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Avaliacao;
import br.com.projetointegrado.model.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Endpoints da API do Projeto Integrado para Avaliações")
public class AvaliacaoController {

	@Autowired
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @Operation(summary = "Lista todas as avaliações", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliações listadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar avaliações"),
    })
    @GetMapping("/listarTodas")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.listarAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }

    @Operation(summary = "Obtém uma avaliação por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação obtida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter avaliação"),
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<Optional<Avaliacao>> obterAvaliacaoPorId(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoService.getAvaliacaoPorId(id);
        if (!avaliacao.isEmpty()) {
            return ResponseEntity.ok(avaliacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Registra uma nova avaliação", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Avaliação registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao registrar avaliação"),
    })
    @PostMapping("/registrar")
    public ResponseEntity<Avaliacao> registrarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoService.adicionarAvaliacao(avaliacao);
        return ResponseEntity.status(201).body(novaAvaliacao);
    }
}