package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Funcionario;
import br.com.projetointegrado.model.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints da API do Projeto Integrado para os Funcionários")
public class FuncionarioController {

	@Autowired
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Lista todos os funcionários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar funcionários"),
    })
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @Operation(summary = "Obtém um funcionário por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário obtido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao obter funcionário"),
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<Optional<Funcionario>> getFuncionarioById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        if (!funcionario.isEmpty()) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um novo funcionário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar funcionário"),
    })
    @PostMapping("/novo")
    public ResponseEntity<Funcionario> adicionarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.save(funcionario);
        return ResponseEntity.status(201).body(novoFuncionario);
    }

    @Operation(summary = "Atualiza um funcionário por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar funcionário"),
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);
        if (funcionarioAtualizado != null) {
            return ResponseEntity.ok(funcionarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um funcionário por ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover funcionário"),
    })
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerFuncionario(@PathVariable Long id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}