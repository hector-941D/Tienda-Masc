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

import com.tienda_masc.tienda_masc.DTO.TipoDTO;
import com.tienda_masc.tienda_masc.Model.Tipo;
import com.tienda_masc.tienda_masc.Services.TipoService;

@RestController
@RequestMapping("/api/v1/Tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping
    public ResponseEntity<List<TipoDTO>> obtenerTodos() {
        return new ResponseEntity<>(tipoService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearTipo(@RequestBody Tipo tipo) {
        try {
            TipoDTO nuevo = tipoService.guardar(tipo);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al ingresar el tipo: " + e.getMessage(), 
                                         HttpStatus.BAD_REQUEST);
        }
    }
}
