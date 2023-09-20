package br.com.projetointegrado.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetointegrado.model.entity.Cliente;
import br.com.projetointegrado.model.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public Cliente atualizarCliente(Long id, Cliente novoCliente) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                cliente.setNome(novoCliente.getNome());
                cliente.setTelefone(novoCliente.getTelefone());
                cliente.setEmail(novoCliente.getEmail());
                // Salve o cliente atualizado
                return clienteRepository.save(cliente);
            })
            .orElse(null);
    }
}