package br.com.projetointegrado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetointegrado.model.entity.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
}