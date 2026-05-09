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

import com.tienda_masc.tienda_masc.DTO.BoletaDTO;
import com.tienda_masc.tienda_masc.Model.Boleta;
import com.tienda_masc.tienda_masc.Services.BoletaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {
    
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<?> todasLasBoletas(){
        List<BoletaDTO> boletas = boletaService.obtenerTodas();
        if(!boletas.isEmpty()){
            return new ResponseEntity<>(boletas, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay boletas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idBoleta}")
    public ResponseEntity<?> boletaPorId(@PathVariable Integer idBoleta){
        try{
            BoletaDTO boletas = boletaService.buscarPorId(idBoleta);
            return new ResponseEntity<>(boletas, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No se encontró la boleta", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarBoleta(@Valid @RequestBody Boleta boleta){
        try {
            BoletaDTO nuevaBoleta = boletaService.crearBoleta(boleta);
            return new ResponseEntity<>(nuevaBoleta, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Error al procesar la venta: " + e.getMessage(), 
                                        HttpStatus.BAD_REQUEST);
        }
    }
}
