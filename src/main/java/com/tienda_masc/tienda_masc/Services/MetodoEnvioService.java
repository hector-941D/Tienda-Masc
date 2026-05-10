package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.MetodoEnvioDTO;
import com.tienda_masc.tienda_masc.Model.MetodoEnvio;
import com.tienda_masc.tienda_masc.Repository.MetodoEnvioRepository;

@Service
public class MetodoEnvioService {
    
    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<MetodoEnvioDTO> obtenerTodos() {
        List<MetodoEnvioDTO> listaDto = new ArrayList<>();
        for (MetodoEnvio metodoenvio : metodoEnvioRepository.findAll()) {
            listaDto.add(convertirADTO(metodoenvio));
        }
        return listaDto;
    }

    public MetodoEnvioDTO guardar(MetodoEnvio metodoenvio) {
        MetodoEnvio metodoEnvioGuardado = metodoEnvioRepository.save(metodoenvio);
        return convertirADTO(metodoEnvioGuardado);
    }

    public MetodoEnvioDTO convertirADTO(MetodoEnvio metodoenvio) {
        MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
        metodoEnvioDTO.setIdMetodoEnvio(metodoenvio.getIdMetodoEnvio());
        metodoEnvioDTO.setTipoEnvio(metodoenvio.getTipoEnvio());

        return metodoEnvioDTO;
    }
}
