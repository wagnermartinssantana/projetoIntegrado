package br.com.projetointegrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetointegrado.model.entity.Produto;
import br.com.projetointegrado.model.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints da API do Projeto Integrado para os Produtos")
public class ProdutoController {

	@Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Lista todos os produtos", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar produtos"),
    })
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Obtém um produto por ID", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto obtido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao obter produto"),
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<Optional<Produto>> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (!produto.isEmpty()) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um novo produto", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar produto"),
    })
    @PostMapping("/novo")
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.save(produto);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @Operation(summary = "Atualiza um produto por ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar produto"),
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um produto por ID", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao remover produto"),
    })
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}