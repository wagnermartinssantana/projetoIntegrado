package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.repository.TransacaoFinanceiraRepository;
import br.com.projetointegrado.model.entity.TransacaoFinanceira;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoFinanceiraService {

	@Autowired
    private final TransacaoFinanceiraRepository transacaoFinanceiraRepository;

    public TransacaoFinanceiraService(TransacaoFinanceiraRepository transacaoFinanceiraRepository) {
        this.transacaoFinanceiraRepository = transacaoFinanceiraRepository;
    }

    public List<TransacaoFinanceira> listarTodasTransacoes() {
        return transacaoFinanceiraRepository.findAll();
    }

    public Optional<TransacaoFinanceira> obterTransacaoPorId(Long id) {
        return transacaoFinanceiraRepository.findById(id);
    }

    public TransacaoFinanceira registrarTransacao(TransacaoFinanceira transacao) {
        return transacaoFinanceiraRepository.save(transacao);
    }
}