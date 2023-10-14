package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.HorarioTrabalho;
import br.com.projetointegrado.model.repository.HorarioTrabalhoRepository;

import java.util.Optional;
import java.util.List;


@Service
public class HorarioTrabalhoService {

	@Autowired
    private final HorarioTrabalhoRepository horarioTrabalhoRepository;

    public HorarioTrabalhoService(HorarioTrabalhoRepository horarioTrabalhoRepository) {
        this.horarioTrabalhoRepository = horarioTrabalhoRepository;
    }

    public Optional<HorarioTrabalho> findById(Long id) {
        return horarioTrabalhoRepository.findById(id);
    }

    public List<HorarioTrabalho> findByFuncionarioId(Long funcionarioId) {
        return horarioTrabalhoRepository.findByFuncionarioId(funcionarioId);
    }

    public HorarioTrabalho save(HorarioTrabalho horarioTrabalho) {
        return horarioTrabalhoRepository.save(horarioTrabalho);
    }

    public HorarioTrabalho update(Long id, HorarioTrabalho novoHorarioTrabalho) {
        return horarioTrabalhoRepository.findById(id)
                .map(horarioTrabalho -> {
                    horarioTrabalho.setDiaSemana(novoHorarioTrabalho.getDiaSemana());
                    horarioTrabalho.setHorarioInicio(novoHorarioTrabalho.getHorarioInicio());
                    horarioTrabalho.setHorarioFim(novoHorarioTrabalho.getHorarioFim());
                    return horarioTrabalhoRepository.save(horarioTrabalho);
                })
                .orElse(null);
    }

    public void deleteById(Long id) {
        horarioTrabalhoRepository.deleteById(id);
    }
}