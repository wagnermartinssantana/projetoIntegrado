package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Agendamento;
import br.com.projetointegrado.model.repository.AgendamentoRepository;

import java.util.Optional;

@Service
public class AgendamentoService {

	@Autowired
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento confirmarAgendamento(Long id) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);
        if (agendamentoOptional.isPresent()) {
            Agendamento agendamento = agendamentoOptional.get();
            return agendamentoRepository.save(agendamento);
        }
        return null;
    }

    public Agendamento reagendarAgendamento(Long id, Agendamento novoAgendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);
        if (agendamentoOptional.isPresent()) {
            Agendamento agendamento = agendamentoOptional.get();
            return agendamentoRepository.save(agendamento);
        }
        return null;
    }

    public void cancelarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}