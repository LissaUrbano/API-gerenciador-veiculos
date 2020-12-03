package com.lissaurbano.api.gerenciadorveiculos.service;

import java.util.List;
import java.util.Optional;

import com.lissaurbano.api.gerenciadorveiculos.dto.VeiculoDTO;
import com.lissaurbano.api.gerenciadorveiculos.model.Veiculo;
import com.lissaurbano.api.gerenciadorveiculos.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo recebeDTO(VeiculoDTO dto){
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(dto.getModelo());
        veiculo.setValorDiaria(dto.getValorDiaria());
        return veiculo;
    }

	public List<Veiculo> getTodosVeiculos() {
		return veiculoRepository.getTodosVeiculos();
	}

	public void removeByCodigo(int codigo) {
        veiculoRepository.remover(getVeiculoByCodigo(codigo));
	}

	public Veiculo update(Veiculo veiculo) {
        getVeiculoByCodigo(veiculo.getCodigo());
        return veiculoRepository.update(veiculo);
	}

	public Veiculo getVeiculoByCodigo(int codigo) {
        Optional<Veiculo> op = veiculoRepository.getVeiculoByCodigo(codigo);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Veiculo nao cadastrado"
                   )
                );
	}

	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.salvar(veiculo);
	}
    
}
