package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.BoletaDTO;
import com.tienda_masc.tienda_masc.Model.Boleta;
import com.tienda_masc.tienda_masc.Repository.BoletaRepository;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public BoletaDTO crearBoleta(Boleta boleta) {
        String ultimoFolio = boletaRepository.findFirstByOrderByIdBoletaDesc()
                .map(Boleta::getNumeroFolio)
                .orElse("000000");
        int siguienteNumero = Integer.parseInt(ultimoFolio) + 1;
        boleta.setNumeroFolio(String.format("%06d", siguienteNumero));

        int neto = boleta.getMontoNeto();
        int iva = (int) Math.round(neto * 0.19); 
        int total = neto + iva;

        boleta.setMontoIva(iva);
        boleta.setMontoTotal(total);

        Boleta boletaGuardada = boletaRepository.save(boleta);
        return convertirADTO(boletaGuardada);
    }

    public List<BoletaDTO> obtenerTodas(){
        List<BoletaDTO> boletas = new ArrayList<>();
        for(Boleta boleta : boletaRepository.findAll()){
            boletas.add(convertirADTO(boleta));
        }
        return boletas;
    }

    public BoletaDTO buscarPorId(Integer idBoleta){
        Boleta boleta = boletaRepository.findById(idBoleta).orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
        return convertirADTO(boleta);
    }


    private BoletaDTO convertirADTO (Boleta boleta){
        BoletaDTO boletaDTO = new BoletaDTO();
        boletaDTO.setIdBoleta(boleta.getIdBoleta());
        boletaDTO.setNumeroFolio(boleta.getNumeroFolio());
        boletaDTO.setFechaEmision(boleta.getFechaEmision());
        boletaDTO.setHoraEmision(boleta.getHoraEmision());
        boletaDTO.setMontoNeto(boleta.getMontoNeto());
        boletaDTO.setMontoIva(boleta.getMontoIva());
        boletaDTO.setMontoTotal(boleta.getMontoTotal());
        if(boleta.getMetodoPago() != null) {
            boletaDTO.setMetodoPago(boleta.getMetodoPago().toString());
        }
        
        return boletaDTO;
    }
}
