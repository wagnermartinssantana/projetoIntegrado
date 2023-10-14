package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.HorarioTrabalho;
import br.com.projetointegrado.model.service.HorarioTrabalhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios-trabalho")
@Tag(name = "Horários de Trabalho", description = "Endpoints para gerenciar os horários de trabalho dos funcionários")
public class HorarioTrabalhoController {

    @Autowired
    private final HorarioTrabalhoService horarioTrabalhoService;

    public HorarioTrabalhoController(HorarioTrabalhoService horarioTrabalhoService) {
        this.horarioTrabalhoService = horarioTrabalhoService;
    }

    @Operation(summary = "Obter um horário de trabalho por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horário de trabalho obtido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Horário de trabalho não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter horário de trabalho"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<HorarioTrabalho> getHorarioTrabalhoById(@PathVariable Long id) {
        Optional<HorarioTrabalho> horarioTrabalho = horarioTrabalhoService.findById(id);
        return horarioTrabalho.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obter horários de trabalho por ID do funcionário", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horários de trabalho obtidos com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter horários de trabalho"),
    })
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<HorarioTrabalho>> getHorariosTrabalhoByFuncionario(@PathVariable Long funcionarioId) {
        List<HorarioTrabalho> horariosTrabalho = horarioTrabalhoService.findByFuncionarioId(funcionarioId);
        return ResponseEntity.ok(horariosTrabalho);
    }

    @Operation(summary = "Criar um novo horário de trabalho", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Horário de trabalho criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar horário de trabalho"),
    })
    @PostMapping
    public ResponseEntity<HorarioTrabalho> createHorarioTrabalho(@RequestBody HorarioTrabalho horarioTrabalho) {
        HorarioTrabalho novoHorarioTrabalho = horarioTrabalhoService.save(horarioTrabalho);
        return ResponseEntity.status(201).body(novoHorarioTrabalho);
    }

    @Operation(summary = "Atualizar um horário de trabalho por ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horário de trabalho atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Horário de trabalho não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar horário de trabalho"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<HorarioTrabalho> updateHorarioTrabalho(
            @PathVariable Long id,
            @RequestBody HorarioTrabalho horarioTrabalho) {
        HorarioTrabalho horarioTrabalhoAtualizado = horarioTrabalhoService.update(id, horarioTrabalho);
        if (horarioTrabalhoAtualizado != null) {
            return ResponseEntity.ok(horarioTrabalhoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um horário de trabalho por ID", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Horário de trabalho excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Horário de trabalho não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao excluir horário de trabalho"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorarioTrabalho(@PathVariable Long id) {
        horarioTrabalhoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}