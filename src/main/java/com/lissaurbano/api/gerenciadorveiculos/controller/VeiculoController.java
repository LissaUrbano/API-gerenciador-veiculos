package com.lissaurbano.api.gerenciadorveiculos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.lissaurbano.api.gerenciadorveiculos.dto.VeiculoDTO;
import com.lissaurbano.api.gerenciadorveiculos.model.Reserva;
import com.lissaurbano.api.gerenciadorveiculos.model.Veiculo;
import com.lissaurbano.api.gerenciadorveiculos.service.VeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping()
    public List<Veiculo> getVeiculos() {
        return veiculoService.getTodosVeiculos();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerVeiculo(@PathVariable int codigo) {
        veiculoService.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@RequestBody VeiculoDTO veiculoDTO, @PathVariable int codigo) {
        Veiculo veiculo = veiculoService.recebeDTO(veiculoDTO);
        veiculo.setCodigo(codigo);
        veiculo = veiculoService.update(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getClienteByCodigo(@PathVariable int codigo) {
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigo);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<Veiculo> salvarCliente(@Valid @RequestBody VeiculoDTO veiculoDTO, HttpServletRequest request,
            UriComponentsBuilder builder) {
        Veiculo veiculo = veiculoService.recebeDTO(veiculoDTO);
        Veiculo novoVeiculo = veiculoService.save(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{idVeiculo}/reservas")
    public List<Reserva> getReservasDoCliente(@PathVariable int idVeiculo) {
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);
        return veiculo.getReservasDoVeiculo();
    }
    
}
