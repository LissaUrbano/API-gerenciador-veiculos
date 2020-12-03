package com.lissaurbano.api.gerenciadorveiculos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.lissaurbano.api.gerenciadorveiculos.model.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class VeiculoRepository {
    
    private List<Veiculo> veiculos;
    private int nextCode;

    @PostConstruct
    public void criarVeiculos() {
        Veiculo veiculo1 = new Veiculo();
        Veiculo veiculo2 = new Veiculo();
        Veiculo veiculo3 = new Veiculo();
        Veiculo veiculo4 = new Veiculo();

        veiculo1.setCodigo(1);
        veiculo1.setModelo("Fiat UNO");
        veiculo1.setValorDiaria(100.10f);

        veiculo2.setCodigo(2);
        veiculo2.setModelo("Fiat Argo");
        veiculo2.setValorDiaria(180.50f);

        veiculo3.setCodigo(3);
        veiculo3.setModelo("GM Onix");
        veiculo3.setValorDiaria(210.80f);

        veiculo4.setCodigo(4);
        veiculo4.setModelo("GM Prisma");
        veiculo4.setValorDiaria(250.00f);


        veiculos = new ArrayList<Veiculo>();
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
        veiculos.add(veiculo4);

        nextCode = 5;
    }
    
    public List<Veiculo> getTodosVeiculos() {
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoByCodigo(int codigo) {
        for (Veiculo aux : veiculos) {
            if (aux.getCodigo() == codigo) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Veiculo salvar(Veiculo veiculo) {
        veiculo.setCodigo(nextCode++);
        veiculos.add(veiculo);
        return veiculo;
    }

	public void remover(Veiculo veiculo) {
        veiculos.remove(veiculo);
	}

	public Veiculo update(Veiculo veiculo) {
        Veiculo aux = getVeiculoByCodigo(veiculo.getCodigo()).get();
        if(aux != null){
            aux.setModelo(veiculo.getModelo());
            aux.setValorDiaria(veiculo.getValorDiaria());
        }
        return aux;
	}
}
