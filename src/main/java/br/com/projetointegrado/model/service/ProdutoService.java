package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Produto;
import br.com.projetointegrado.model.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

	@Autowired
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Long id, Produto novoProduto) {
        return produtoRepository.findById(id)
            .map(produto -> {
                produto.setNome(novoProduto.getNome());
                produto.setDescricao(novoProduto.getDescricao());
                produto.setPreco(novoProduto.getPreco());
                produto.setQuantidade(novoProduto.getQuantidade());
                return produtoRepository.save(produto);
            })
            .orElse(null);
    }
}