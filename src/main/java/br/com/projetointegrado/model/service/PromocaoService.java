package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Promocao;
import br.com.projetointegrado.model.repository.PromocaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PromocaoService {

	@Autowired
    private final PromocaoRepository promocaoRepository;

    public PromocaoService(PromocaoRepository promocaoRepository) {
        this.promocaoRepository = promocaoRepository;
    }

    public List<Promocao> listarPromocoes() {
        return promocaoRepository.findAll();
    }

    public Optional<Promocao> obterPromocaoPorId(Long id) {
        return promocaoRepository.findById(id);
    }

    public Promocao criarPromocao(Promocao promocao) {
        return promocaoRepository.save(promocao);
    }

    public Promocao atualizarPromocao(Long id, Promocao promocao) {
        return promocaoRepository.findById(id)
            .map(existingPromocao -> {
                existingPromocao.setServico(promocao.getServico());
                existingPromocao.setDescricao(promocao.getDescricao());
                existingPromocao.setDesconto(promocao.getDesconto());
                existingPromocao.setDataInicio(promocao.getDataInicio());
                existingPromocao.setDataFim(promocao.getDataFim());
                return promocaoRepository.save(existingPromocao);
            })
            .orElse(null);
    }

    public void removerPromocao(Long id) {
        promocaoRepository.deleteById(id);
    }
}