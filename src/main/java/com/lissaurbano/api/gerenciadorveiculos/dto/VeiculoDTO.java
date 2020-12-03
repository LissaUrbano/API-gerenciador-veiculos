package com.lissaurbano.api.gerenciadorveiculos.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class VeiculoDTO {
    
    @NotBlank(message = "Modelo obrigatório!")
    @Length(min=2,max=20, message = "Nome deve ter no mínimo 2 e no máximo 20 caracteres!")
    private String modelo;
    
    @NotBlank(message = "Valor da diária é obrigatório!")
    private Float valorDiaria;

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

}
