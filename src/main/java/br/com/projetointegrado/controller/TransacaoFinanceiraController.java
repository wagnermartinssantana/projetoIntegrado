package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.TransacaoFinanceira;
import br.com.projetointegrado.model.service.TransacaoFinanceiraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes-financeiras")
@Tag(name = "Transações Financeiras", description = "Endpoints da API para Transações Financeiras")
public class TransacaoFinanceiraController {

	@Autowired
    private final TransacaoFinanceiraService transacaoFinanceiraService;

    public TransacaoFinanceiraController(TransacaoFinanceiraService transacaoFinanceiraService) {
        this.transacaoFinanceiraService = transacaoFinanceiraService;
    }

    @Operation(summary = "Lista todas as transações financeiras", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transações financeiras listadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar transações financeiras"),
    })
    @GetMapping("/listar-todas")
    public ResponseEntity<List<TransacaoFinanceira>> listarTransacoesFinanceiras() {
        List<TransacaoFinanceira> transacoes = transacaoFinanceiraService.listarTodasTransacoes();
        return ResponseEntity.ok(transacoes);
    }

    @Operation(summary = "Obtém uma transação financeira por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transação financeira obtida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Transação financeira não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter transação financeira"),
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<Optional<TransacaoFinanceira>> getTransacaoFinanceiraById(@PathVariable Long id) {
        Optional<TransacaoFinanceira> transacao = transacaoFinanceiraService.obterTransacaoPorId(id);
        if (transacao.isPresent()) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Registrar uma nova transação financeira", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação financeira registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao registrar transação financeira"),
    })
    @PostMapping("/registrar")
    public ResponseEntity<TransacaoFinanceira> registrarTransacaoFinanceira(@RequestBody TransacaoFinanceira transacao) {
        TransacaoFinanceira novaTransacao = transacaoFinanceiraService.registrarTransacao(transacao);
        return ResponseEntity.status(201).body(novaTransacao);
    }
}