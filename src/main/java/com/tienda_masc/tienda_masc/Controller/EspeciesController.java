package com.tienda_masc.tienda_masc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_masc.tienda_masc.DTO.EspeciesDTO;
import com.tienda_masc.tienda_masc.Model.Especies;
import com.tienda_masc.tienda_masc.Services.EspeciesService;

@RestController
@RequestMapping("/api/v1/especies")
public class EspeciesController {

    @Autowired
    private EspeciesService especiesService;

    @GetMapping
    public ResponseEntity<List<EspeciesDTO>> obtenerTodos(){
        List<EspeciesDTO> especies = especiesService.obtenerTodos();
        if(especies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(especies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspeciesDTO> buscarPorId(@PathVariable Integer id){
        try{
            EspeciesDTO especie = especiesService.buscarPorId(id);
            return new ResponseEntity<>(especie, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Especies> guardar(@RequestBody Especies especie){
        try{
            Especies guardada = especiesService.guardar(especie);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especies> actualizar(@PathVariable Integer id, @RequestBody Especies especie){
        try{
            Especies actualizada = especiesService.actualizar(id, especie);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        String resultado = especiesService.eliminar(id);
        if(resultado.contains("correctamente")){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}
