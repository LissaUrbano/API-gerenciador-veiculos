package com.lissaurbano.api.gerenciadorveiculos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import com.lissaurbano.api.gerenciadorveiculos.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {
    
    private List<Cliente> clientes;
    private int nextCode;

    @PostConstruct
    public void criarClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        Cliente cliente4 = new Cliente();
        Cliente cliente5 = new Cliente();

        cliente1.setCodigo(1);
        cliente1.setNome("João");
        cliente1.setEndereco("Rua josé Maria da Silva, 265");
        cliente1.setCpf("4265986287");

        cliente2.setCodigo(2);
        cliente2.setNome("Maria");
        cliente2.setEndereco("Rua Pedro Salon, 55");
        cliente2.setCpf("1265586233");

        cliente3.setCodigo(3);
        cliente3.setNome("Ana");
        cliente3.setEndereco("Rua Bromálias, 16");
        cliente3.setCpf("1565586268");

        cliente4.setCodigo(4);
        cliente4.setNome("Ricardo");
        cliente4.setEndereco("Rua José Iram Marino, 1500");
        cliente4.setCpf("2265986844");

        cliente5.setCodigo(5);
        cliente5.setNome("Pedro");
        cliente5.setEndereco("Rua Eng. Emerenciano Rocha, 2800");
        cliente5.setCpf("2955986226");

        clientes = new ArrayList<Cliente>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);

        nextCode = 6;
    }
    
    public List<Cliente> getTodosClientes() {
        return clientes;
    }

    public Optional<Cliente> getClienteByCodigo(int codigo) {
        for (Cliente aux : clientes) {
            if (aux.getCodigo() == codigo) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

	public void remover(Cliente cliente) {
        clientes.remove(cliente);
	}

	public Cliente update(Cliente cliente) {
        Cliente aux = getClienteByCodigo(cliente.getCodigo()).get();
        if(aux != null){
            aux.setNome(cliente.getNome());
            aux.setEndereco(cliente.getEndereco());
            aux.setCpf(cliente.getCpf());
        }
        return aux;
	}
}
