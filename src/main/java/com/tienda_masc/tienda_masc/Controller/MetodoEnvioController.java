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

import com.tienda_masc.tienda_masc.DTO.MetodoEnvioDTO;
import com.tienda_masc.tienda_masc.Model.MetodoEnvio;
import com.tienda_masc.tienda_masc.Services.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodos-envio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvioDTO>> obtenerTodos() {
        return new ResponseEntity<>(metodoEnvioService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        try {
            MetodoEnvioDTO nuevo = metodoEnvioService.guardar(metodoEnvio);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear método de envío: " + e.getMessage(), 
                                         HttpStatus.BAD_REQUEST);
        }
    }
}
