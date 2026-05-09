package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.MetodoPagoDTO;
import com.tienda_masc.tienda_masc.Model.MetodoPago;
import com.tienda_masc.tienda_masc.Repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repository;

    public List<MetodoPagoDTO> obtenerTodos() {
        List<MetodoPagoDTO> listaDto = new ArrayList<>();
        for (MetodoPago metodopago : repository.findAll()) {
            listaDto.add(convertirADTO(metodopago));
        }
        return listaDto;
    }

    public MetodoPagoDTO guardar(MetodoPago metodopago) {
        MetodoPago guardado = repository.save(metodopago);
        return convertirADTO(guardado);
    }

    public MetodoPagoDTO convertirADTO(MetodoPago metodopago) {
        MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
        metodoPagoDTO.setIdMetodoPago(metodopago.getIdMetodoPago());
        metodoPagoDTO.setNombreMetodoPago(metodopago.getNombreMetodoPago());
        return metodoPagoDTO;
    }
}