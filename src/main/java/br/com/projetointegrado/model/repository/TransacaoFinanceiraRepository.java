package br.com.projetointegrado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetointegrado.model.entity.TransacaoFinanceira;

@Repository
public interface TransacaoFinanceiraRepository extends JpaRepository<TransacaoFinanceira, Long> {
}