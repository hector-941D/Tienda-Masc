package com.tienda_masc.tienda_masc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.ProductosDTO;
import com.tienda_masc.tienda_masc.Model.Productos;
import com.tienda_masc.tienda_masc.Repository.ProductosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductosRepository productosRepository;

    private ProductosDTO convertirADTO(Productos productos) {
        ProductosDTO dto = new ProductosDTO();
        dto.setIdProducto(productos.getIdProductos());
        dto.setNombreProductos(productos.getNombreProductos());
        dto.setNombreTipo(productos.getTipo().getNombreDelTipo());
        dto.setNombreMarca(productos.getMarca().getNombreMarca());
        dto.setNombreEspecie(productos.getEspecie().getNombreEspecie());

        return dto;
    }

    public List<ProductosDTO> obtenerTodos(){
        return productosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductosDTO buscarPorId(Integer id){
        Productos producto = productosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado!!"));
        return convertirADTO(producto);  
    }

    public String eliminar (Integer id){
        try {
            Productos producto = productosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se pudo eliminar. La ID " + id + " No existe"));
            productosRepository.delete(producto);
            return "El Producto " + producto.getNombreProductos() + " Ha sido retirado exitosamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Productos guardarProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    public Productos actualizar(Integer id, Productos producto) {
        Productos productos = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto"));
        if(producto.getNombreProductos()!= null){
            productos.setNombreProductos(producto.getNombreProductos());
        }
        if(producto.getPrecio()!= null){
            productos.setPrecio(producto.getPrecio());
        }
        return productosRepository.save(productos);
    }

    public List<ProductosDTO> buscarPorTipo(String nombreDelTipo){
    return productosRepository.findByTipoNombreDelTipo(nombreDelTipo).stream()
            .map(this::convertirADTO)
            .toList();
    }
}
