package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Promocao;
import br.com.projetointegrado.model.service.PromocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promocoes")
@Tag(name = "Promoções", description = "Endpoints da API para gerenciar promoções")
public class PromocaoController {

    @Autowired
    private final PromocaoService promocaoService;

    public PromocaoController(PromocaoService promocaoService) {
        this.promocaoService = promocaoService;
    }

    @Operation(summary = "Listar todas as promoções", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promoções listadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar promoções"),
    })
    @GetMapping
    public ResponseEntity<List<Promocao>> listarPromocoes() {
        List<Promocao> promocoes = promocaoService.listarPromocoes();
        return ResponseEntity.ok(promocoes);
    }

    @Operation(summary = "Obter uma promoção por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promoção obtida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Promoção não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter promoção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Promocao> obterPromocaoPorId(@PathVariable Long id) {
        Optional<Promocao> promocao = promocaoService.obterPromocaoPorId(id);
        return promocao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar uma nova promoção", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Promoção criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar promoção"),
    })
    @PostMapping
    public ResponseEntity<Promocao> criarPromocao(@RequestBody Promocao promocao) {
        Promocao novaPromocao = promocaoService.criarPromocao(promocao);
        return ResponseEntity.status(201).body(novaPromocao);
    }

    @Operation(summary = "Atualizar uma promoção por ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promoção atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Promoção não encontrada"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar promoção"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Promocao> atualizarPromocao(@PathVariable Long id, @RequestBody Promocao promocao) {
        Promocao promocaoAtualizada = promocaoService.atualizarPromocao(id, promocao);
        if (promocaoAtualizada != null) {
            return ResponseEntity.ok(promocaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remover uma promoção por ID", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Promoção removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Promoção não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao remover promoção"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPromocao(@PathVariable Long id) {
        promocaoService.removerPromocao(id);
        return ResponseEntity.noContent().build();
    }
}