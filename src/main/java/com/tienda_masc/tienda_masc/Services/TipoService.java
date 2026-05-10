package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.TipoDTO;
import com.tienda_masc.tienda_masc.Model.Tipo;
import com.tienda_masc.tienda_masc.Repository.TipoRepository;

@Service
public class TipoService {
    
    @Autowired
    private TipoRepository tipoRepository;

    public List<TipoDTO> obtenerTodos() {
        List<TipoDTO> listaDto = new ArrayList<>();
        for (Tipo tipos : tiposRepository.findAll()) {
            listaDto.add(convertirADTO(tipos));
        }
        return listaDto;
    }

    public TipoDTO guardar(Tipo tipos) {
        Tipo tiposGuardado = tipoRepository.save(tipos);
        return convertirADTO(tipoGuardado);
    }

    public TipoDTO convertirADTO(Tipo tipo) {
        TipoDTO tipoDTO = new TipoDTO();
        TipoDTO.setIdTipo(tipo.getIdTipo());
        TipoDTO.setTipos(tipo.getTipos());

        return tiposDTO;
    }
}
