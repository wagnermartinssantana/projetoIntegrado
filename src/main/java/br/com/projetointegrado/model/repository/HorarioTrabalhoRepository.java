package br.com.projetointegrado.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetointegrado.model.entity.HorarioTrabalho;

@Repository
public interface HorarioTrabalhoRepository extends JpaRepository<HorarioTrabalho, Long> {

	List<HorarioTrabalho> findByFuncionarioId(Long funcionarioId);
}