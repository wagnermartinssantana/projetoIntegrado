package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Avaliacao;
import br.com.projetointegrado.model.repository.AvaliacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

	@Autowired
    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> getAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public Avaliacao adicionarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao novaAvaliacao) {
        return avaliacaoRepository.findById(id)
                .map(avaliacao -> {
                    avaliacao.setCliente(novaAvaliacao.getCliente());
                    avaliacao.setServico(novaAvaliacao.getServico());
                    avaliacao.setEstrelas(novaAvaliacao.getEstrelas());
                    avaliacao.setComentario(novaAvaliacao.getComentario());
                    avaliacao.setDataAtendimento(novaAvaliacao.getDataAtendimento());
                    return avaliacaoRepository.save(avaliacao);
                })
                .orElse(null);
    }

    public void removerAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}