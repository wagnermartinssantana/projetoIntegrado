package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Cliente;
import br.com.projetointegrado.model.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes", description = "Endpoints da API do Projeto Integrado para os Clientes")
public class ClienteController {
	
	@Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Lista todos os clientes", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar clientes"),
    })
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obtém um cliente por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente obtido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter cliente"),
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<Optional<Cliente>> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (!cliente.isEmpty()) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um novo cliente", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar cliente"),
    })
    @PostMapping("/novo")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.save(cliente);
        return ResponseEntity.status(201).body(novoCliente);
    }

    @Operation(summary = "Atualiza um cliente por ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar cliente"),
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        if (clienteAtualizado != null) {
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um cliente por ID", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao remover cliente"),
    })
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
