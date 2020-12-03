package com.lissaurbano.api.gerenciadorveiculos.service;

import java.util.List;
import java.util.Optional;

import com.lissaurbano.api.gerenciadorveiculos.model.Cliente;
import com.lissaurbano.api.gerenciadorveiculos.model.Reserva;
import com.lissaurbano.api.gerenciadorveiculos.model.Veiculo;
import com.lissaurbano.api.gerenciadorveiculos.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteService  clienteService;

    @Autowired
    private VeiculoService  veiculoService;


    public List<Reserva> getTodasReservas(){
        return reservaRepository.getTodasReservas();
    }
    
    public Reserva getReservaByNumero(long numero) {
        Optional <Reserva> op = reservaRepository.getReservaByNumero(numero);
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao cadastrada"));
    }


    public Reserva salvarReserva(Reserva reserva, int idCliente, int idVeiculo){

        if (reserva.isNotDomingo() && reserva.validacaoDatas()) {

            Cliente cliente = clienteService.getClienteByCodigo(idCliente);
            Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);

            reserva.setDataInicio(reserva.getDataInicio());     
            reserva.setDataFim(reserva.getDataFim());
            
            reserva.setCliente(cliente);
            reserva.setVeiculo(veiculo);
            cliente.addReserva(reserva);
            veiculo.addReserva(reserva);

        return reservaRepository.salvar(reserva);
        } else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!"); }
    }
}
