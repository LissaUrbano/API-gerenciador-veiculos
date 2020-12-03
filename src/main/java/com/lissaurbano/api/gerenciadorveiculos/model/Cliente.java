package com.lissaurbano.api.gerenciadorveiculos.model;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {
    
    private int codigo;
    private String nome;
    private String endereco;
    private String cpf;

    @JsonIgnore
    private ArrayList<Reserva> reservasDoCliente = new ArrayList<Reserva>();


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    // Gerencia/CRUD lista Reservas do cliente
    public ArrayList<Reserva> getReservasDoCliente() {
        return reservasDoCliente;
    }

    public void setReservasDoCliente(ArrayList<Reserva> reservasDoCliente) {
        this.reservasDoCliente = reservasDoCliente;
    }
  
    public boolean addReserva(Reserva reserva) {
        return reservasDoCliente.add(reserva);
    }

    public boolean removeReserva(Reserva reserva) {
        return reservasDoCliente.remove(reserva);
    }

}
