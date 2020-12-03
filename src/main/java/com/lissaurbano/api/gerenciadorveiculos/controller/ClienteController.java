package com.lissaurbano.api.gerenciadorveiculos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.lissaurbano.api.gerenciadorveiculos.dto.ClienteDTO;
import com.lissaurbano.api.gerenciadorveiculos.model.Cliente;
import com.lissaurbano.api.gerenciadorveiculos.model.Reserva;
import com.lissaurbano.api.gerenciadorveiculos.service.ClienteService;
import com.lissaurbano.api.gerenciadorveiculos.service.ReservaService;

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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService  clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public List<Cliente> getClientes() {
        return clienteService.getTodosClientes();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerCliente(@PathVariable int codigo) {
        clienteService.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable int codigo) {
        Cliente cliente = clienteService.recebeDTO(clienteDTO);
        cliente.setCodigo(codigo);
        cliente = clienteService.update(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int codigo) {
        Cliente cliente = clienteService.getClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Cliente> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request,
            UriComponentsBuilder builder) {
        Cliente cliente = clienteService.recebeDTO(clienteDTO);
        Cliente novoCliente = clienteService.save(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    @PostMapping("/{id-cliente}/veiculos/{id-veiculo}")
    public ResponseEntity<Reserva> salvar(@PathVariable int idCliente,@PathVariable int idVeiculo, @RequestBody Reserva reserva, 
            HttpServletRequest request, UriComponentsBuilder builder) {
                reserva = reservaService.salvarReserva(reserva, idCliente, idVeiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + reserva.getNumero()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{idCliente}/reservas")
    public List<Reserva> getReservasDoCliente(@PathVariable int idCliente) {
        Cliente cliente = clienteService.getClienteByCodigo(idCliente);
        return cliente.getReservasDoCliente();
    }

}
