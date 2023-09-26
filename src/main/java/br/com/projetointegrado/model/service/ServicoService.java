package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Servico;
import br.com.projetointegrado.model.repository.ServicoRepository;

import java.util.Optional;

import java.util.List;

@Service
public class ServicoService {

	@Autowired
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> getServicoPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico adicionarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizarServico(Long id, Servico novoServico) {
        return servicoRepository.findById(id)
            .map(servico -> {
                servico.setNome(novoServico.getNome());
                servico.setDescricao(novoServico.getDescricao());
                servico.setDuracao(novoServico.getDuracao());
                servico.setPreco(novoServico.getPreco());
                return servicoRepository.save(servico);
            })
            .orElse(null);
    }

    public void removerServico(Long id) {
        servicoRepository.deleteById(id);
    }
}