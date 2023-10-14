package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Avaliacao;
import br.com.projetointegrado.model.entity.Cliente;
import br.com.projetointegrado.model.entity.Servico;
import br.com.projetointegrado.model.repository.AvaliacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

	@Autowired
    private final AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
    private ClienteService clienteService;
	
	@Autowired
    private ServicoService servicoService;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> getAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public Avaliacao adicionarAvaliacao(Avaliacao avaliacao) throws Exception {
    	
    	
    	Optional<Servico> servicoOptional = servicoService.getServicoPorId(1L);
        Optional<Cliente> clienteOptional = clienteService.findById(4L);

        if (clienteOptional.isPresent() && servicoOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            avaliacao.setCliente(cliente);
            
            Servico servico = servicoOptional.get();
            avaliacao.setServico(servico);
            
            
            return avaliacaoRepository.save(avaliacao);
        } else {
            throw new Exception("Cliente com ID " + 4 + " não encontrado.");
        }
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao novaAvaliacao) {
        return avaliacaoRepository.findById(id)
                .map(avaliacao -> {
                    avaliacao.setCliente(novaAvaliacao.getCliente());
                    avaliacao.setServico(novaAvaliacao.getServico());
                    avaliacao.setEstrelas(novaAvaliacao.getEstrelas());
                    avaliacao.setComentario(novaAvaliacao.getComentario());
                    avaliacao.setDataAvalicao(novaAvaliacao.getDataAvaliacao());
                    return avaliacaoRepository.save(avaliacao);
                })
                .orElse(null);
    }

    public void removerAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}