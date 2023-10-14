package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Agendamento;
import br.com.projetointegrado.model.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints da API para Agendamentos")
public class AgendamentoController {

	@Autowired
    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Operation(summary = "Confirma um agendamento", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento confirmado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao confirmar agendamento"),
    })
    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Agendamento> confirmarAgendamento(@PathVariable Long id) {
        Agendamento agendamentoConfirmado = agendamentoService.confirmarAgendamento(id);
        if (agendamentoConfirmado != null) {
            return ResponseEntity.ok(agendamentoConfirmado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Reagenda um agendamento", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento reagendado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao reagendar agendamento"),
    })
    @PutMapping("/reagendar/{id}")
    public ResponseEntity<Agendamento> reagendarAgendamento(@PathVariable Long id, @RequestBody Agendamento novoAgendamento) {
        Agendamento agendamentoReagendado = agendamentoService.reagendarAgendamento(id, novoAgendamento);
        if (agendamentoReagendado != null) {
            return ResponseEntity.ok(agendamentoReagendado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cancelar um agendamento", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao cancelar agendamento"),
    })
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}