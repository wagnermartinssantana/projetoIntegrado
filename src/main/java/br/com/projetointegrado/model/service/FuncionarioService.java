package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Funcionario;
import br.com.projetointegrado.model.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

	@Autowired
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteById(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario novoFuncionario) {
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionario.setNome(novoFuncionario.getNome());
                funcionario.setTelefone(novoFuncionario.getTelefone());
                funcionario.setEmail(novoFuncionario.getEmail());
                funcionario.setRua(novoFuncionario.getRua());
                funcionario.setNumero(novoFuncionario.getNumero());
                funcionario.setComplemento(novoFuncionario.getComplemento());
                funcionario.setBairro(novoFuncionario.getBairro());
                funcionario.setCidade(novoFuncionario.getCidade());
                funcionario.setEstado(novoFuncionario.getEstado());
                funcionario.setPais(novoFuncionario.getPais());
                funcionario.setCargo(novoFuncionario.getCargo());
                funcionario.setSalario(novoFuncionario.getSalario());
                return funcionarioRepository.save(funcionario);
            })
            .orElse(null);
    }
}