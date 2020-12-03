package com.lissaurbano.api.gerenciadorveiculos.controller;

import java.util.List;

import com.lissaurbano.api.gerenciadorveiculos.model.Reserva;
import com.lissaurbano.api.gerenciadorveiculos.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getTodasReservas(){
        return reservaService.getTodasReservas();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Reserva> getPedidoByNumero(@PathVariable long numero) {
        Reserva reserva = reservaService.getReservaByNumero(numero);
        return ResponseEntity.ok(reserva);	
    }

}
