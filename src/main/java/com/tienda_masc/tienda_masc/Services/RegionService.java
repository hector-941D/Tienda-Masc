package com.tienda_masc.tienda_masc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.RegionDTO;
import com.tienda_masc.tienda_masc.Model.Region;
import com.tienda_masc.tienda_masc.Repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    private RegionDTO convertirADTO(Region region){
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombreRegion(region.getNombreRegion());
        return dto;
    }

    public List<RegionDTO> obtenerTodos(){
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id){
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region no encontrada"));
        return convertirADTO(region);
    }

    public Region guardar(Region region){
        return regionRepository.save(region);
    }

    public Region actualizar(Integer id, Region region){
        Region regionExistente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region no encontrada"));
        if(region.getNombreRegion() != null){
            regionExistente.setNombreRegion(region.getNombreRegion());
        }
        return regionRepository.save(regionExistente);
    }

    public String eliminar(Integer id){
        try{
            Region region = regionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Region no encontrada"));
            regionRepository.delete(region);
            return "Region eliminada correctamente";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
} 