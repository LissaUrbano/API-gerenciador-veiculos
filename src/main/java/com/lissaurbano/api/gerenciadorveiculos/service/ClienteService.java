package com.lissaurbano.api.gerenciadorveiculos.service;

import java.util.List;
import java.util.Optional;

import com.lissaurbano.api.gerenciadorveiculos.dto.ClienteDTO;
import com.lissaurbano.api.gerenciadorveiculos.model.Cliente;
import com.lissaurbano.api.gerenciadorveiculos.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente recebeDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setEndereco(dto.getEndereco());
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }

	public List<Cliente> getTodosClientes() {
		return clienteRepository.getTodosClientes();
	}

	public void removeByCodigo(int codigo) {
        clienteRepository.remover(getClienteByCodigo(codigo));
	}

	public Cliente update(Cliente cliente) {
        getClienteByCodigo(cliente.getCodigo());
        return clienteRepository.update(cliente);
	}

	public Cliente getClienteByCodigo(int codigo) {
        Optional<Cliente> op = clienteRepository.getClienteByCodigo(codigo);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cliente nao cadastrado"
                   )
                );
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.salvar(cliente);
	}
    
}
