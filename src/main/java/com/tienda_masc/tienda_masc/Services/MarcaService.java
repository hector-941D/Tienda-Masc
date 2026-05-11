package com.tienda_masc.tienda_masc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.MarcaDTO;
import com.tienda_masc.tienda_masc.Model.Marca;
import com.tienda_masc.tienda_masc.Model.Productos;
import com.tienda_masc.tienda_masc.Repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaDTO convertirADTO(Marca marca){
        MarcaDTO dto = new MarcaDTO();
        dto.setIdMarca(marca.getIdMarca());
        dto.setNombreMarca(marca.getNombreMarca());
        dto.setNombreProductos(
                marca.getProductos().stream()
                        .map(Productos::getNombreProductos)
                        .toList());
        return dto;
    }

    public List<MarcaDTO> obtenerTodos(){
        return marcaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MarcaDTO buscarPorId(Integer id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        return convertirADTO(marca);
    }

    public Marca guardar(Marca marca){
        return marcaRepository.save(marca);
    }

    public Marca actualizar(Integer id, Marca marca){
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        if(marca.getNombreMarca() != null){
            marcaExistente.setNombreMarca(marca.getNombreMarca());
        }
        return marcaRepository.save(marcaExistente);
    }

    public String eliminar(Integer id){
        try{
            Marca marca = marcaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La marca no existe"));
            marcaRepository.delete(marca);
            return "La marca " + marca.getNombreMarca() + " fue eliminada correctamente";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
}
