package com.tienda_masc.tienda_masc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.EspeciesDTO;
import com.tienda_masc.tienda_masc.Model.Especies;
import com.tienda_masc.tienda_masc.Model.Productos;
import com.tienda_masc.tienda_masc.Repository.EspeciesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspeciesService {

    @Autowired
    private EspeciesRepository especiesRepository;

    private EspeciesDTO convertirADTO(Especies especie){
        EspeciesDTO dto = new EspeciesDTO();
        dto.setIdEspecie(especie.getIdEspecies());
        dto.setNombreEspecie(especie.getNombreEspecie());
        dto.setNombreProductos(
                especie.getProductos().stream()
                        .map(Productos::getNombreProductos)
                        .toList());
        return dto;
    }

    public List<EspeciesDTO> obtenerTodos(){
        return especiesRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public EspeciesDTO buscarPorId(Integer id){
        Especies especie = especiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));
        return convertirADTO(especie);
    }

    public Especies guardar(Especies especie){
        return especiesRepository.save(especie);
    }

    public Especies actualizar(Integer id, Especies especie){
        Especies especieExistente = especiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));
        if(especie.getNombreEspecie() != null){
            especieExistente.setNombreEspecie(especie.getNombreEspecie());
        }
        return especiesRepository.save(especieExistente);
    }

    public String eliminar(Integer id){
        try{
            Especies especie = especiesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La especie no existe"));
            especiesRepository.delete(especie);
            return "La especie " + especie.getNombreEspecie() + " fue eliminada correctamente";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
}
