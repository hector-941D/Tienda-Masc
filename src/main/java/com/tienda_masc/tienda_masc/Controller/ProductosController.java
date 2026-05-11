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

import com.tienda_masc.tienda_masc.DTO.ProductosDTO;
import com.tienda_masc.tienda_masc.Model.Productos;
import com.tienda_masc.tienda_masc.Services.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductosDTO>> obtenerTodos(){
        List<ProductosDTO> productos = productoService.obtenerTodos();
        if(productos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> buscarPorId(@PathVariable Integer id){
        try{
            ProductosDTO producto = productoService.buscarPorId(id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Productos> guardarProducto(@RequestBody Productos producto){
        try{
            Productos guardado = productoService.guardarProducto(producto);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Integer id,@RequestBody Productos producto){
        try{
            Productos actualizado = productoService.actualizar(id, producto);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id){
        String resultado = productoService.eliminar(id);
        if(resultado.contains("exitosamente")){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tipo/{nombreTipo}")
    public ResponseEntity<List<ProductosDTO>> buscarPorTipo(@PathVariable String nombreTipo){
    List<ProductosDTO> productos = productoService.buscarPorTipo(nombreTipo);
    if(productos.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(productos, HttpStatus.OK);
}

}
