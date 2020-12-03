package com.lissaurbano.api.gerenciadorveiculos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lissaurbano.api.gerenciadorveiculos.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private int nextNumero = 1;

    public List<Reserva> getTodasReservas() {
        return reservas;
    }

    public Optional<Reserva> getReservaByNumero(long numero) {
        for (Reserva aux : reservas) {
            if (aux.getNumero() == numero) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Reserva salvar(Reserva reserva) {
        reserva.setNumero(nextNumero++);
        reserva.setCliente(reserva.getCliente());
        reserva.setVeiculo(reserva.getVeiculo());
        reserva.setDataInicio(reserva.getDataInicio());
        reserva.setDataFim(reserva.getDataFim());
        reservas.add(reserva);
        return reserva;
    }

}
