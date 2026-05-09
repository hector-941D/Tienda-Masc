package com.tienda_masc.tienda_masc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_masc.tienda_masc.DTO.MetodoPagoDTO;
import com.tienda_masc.tienda_masc.Model.MetodoPago;
import com.tienda_masc.tienda_masc.Services.MetodoPagoService;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> obtenerTodos() {
        return new ResponseEntity<>(metodoPagoService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        try {
            MetodoPagoDTO nuevo = metodoPagoService.guardar(metodoPago);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear método de pago: " + e.getMessage(), 
                                         HttpStatus.BAD_REQUEST);
        }
    }
}
