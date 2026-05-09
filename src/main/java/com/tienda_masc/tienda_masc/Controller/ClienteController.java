package com.tienda_masc.tienda_masc.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_masc.tienda_masc.DTO.ClienteDTO;
import com.tienda_masc.tienda_masc.Model.Cliente;
import com.tienda_masc.tienda_masc.Services.ClienteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    // Obtener todos los clientes (con sus productos comprados "aplanados")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = clienteServices.obtenerTodas();
        return ResponseEntity.ok(clientes);
    }

    // Obtener un cliente específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            ClienteDTO cliente = clienteServices.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarCliente(@Valid @RequestBody Cliente cliente){
        try {
            ClienteDTO nuevoCliente = clienteServices.guardarCliente(cliente);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Error al procesar la venta: " + e.getMessage(), 
                                        HttpStatus.BAD_REQUEST);
        }
    }
}

