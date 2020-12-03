package com.lissaurbano.api.gerenciadorveiculos.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Veiculo {
    
    private int codigo;
    private String modelo;
    private Float valorDiaria;

	@JsonIgnore
    private ArrayList<Reserva> reservasDoVeiculo = new ArrayList<Reserva>();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Float getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Float valorDiaria) {
		this.valorDiaria = valorDiaria;
	}


	// Gerencia/CRUD lista Reservas do veiculo
	public ArrayList<Reserva> getReservasDoVeiculo() {
		return reservasDoVeiculo;
	}

	public void setReservasDoVeiculo(ArrayList<Reserva> reservasDoVeiculo) {
		this.reservasDoVeiculo = reservasDoVeiculo;
	}

    public boolean addReserva(Reserva reserva) {
        return reservasDoVeiculo.add(reserva);
    }

    public boolean removeReserva(Reserva reserva) {
        return reservasDoVeiculo.remove(reserva);
    }
}
