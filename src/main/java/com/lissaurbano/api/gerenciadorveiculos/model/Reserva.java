package com.lissaurbano.api.gerenciadorveiculos.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class Reserva {
    
    private int numero;
    private Veiculo veiculo;
    private Cliente cliente;
    private double totalReserva;
    
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataFim;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotalReserva() {
        float diarias = ChronoUnit.DAYS.between(dataInicio, dataFim);
        totalReserva = diarias * veiculo.getValorDiaria();
        return totalReserva;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    @JsonIgnore
    public boolean validacaoDatas() {
        return (getDataInicio().isAfter(LocalDateTime.now()) && getDataFim().isAfter(getDataInicio()));
    }

    @JsonIgnore
    public boolean isNotDomingo() {
        DayOfWeek initialDay = getDataInicio().getDayOfWeek();
        DayOfWeek finalDay = getDataFim().getDayOfWeek();
        return !(initialDay == DayOfWeek.SUNDAY || finalDay == DayOfWeek.SUNDAY);
    }

    @Override
    public String toString() {
        return "Reserva [cliente=" + cliente + ", dataFim=" + dataFim + ", dataInicio=" + dataInicio + ", numero="
                + numero + ", totalReserva=" + totalReserva + ", veiculo=" + veiculo + "]";
    }

    

}
