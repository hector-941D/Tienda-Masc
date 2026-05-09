package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.BoletaDTO;
import com.tienda_masc.tienda_masc.Model.Boleta;
import com.tienda_masc.tienda_masc.Repository.BoletaRepository;
import com.tienda_masc.tienda_masc.Repository.ClienteRepository;
import com.tienda_masc.tienda_masc.Repository.MetodoEnvioRepository;
import com.tienda_masc.tienda_masc.Repository.MetodoPagoRepository;

@Service
public class BoletaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

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

        if (boleta.getCliente() != null) {
        boleta.setCliente(clienteRepository.findById(boleta.getCliente().getIdCliente()).orElse(null));
        }
    
        if (boleta.getMetodoPago() != null) {
            boleta.setMetodoPago(metodoPagoRepository.findById(boleta.getMetodoPago().getIdMetodoPago()).orElse(null));
        }
    
        if (boleta.getMetodoEnvio() != null) {
            boleta.setMetodoEnvio(metodoEnvioRepository.findById(boleta.getMetodoEnvio().getIdMetodoEnvio()).orElse(null));
        }

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

        if (boleta.getCliente() != null) {
            boletaDTO.setIdCliente(boleta.getCliente().getIdCliente());
        }

        if (boleta.getMetodoPago() != null) {
            boletaDTO.setMetodoPago(boleta.getMetodoPago().getNombreMetodoPago());
        }

        if (boleta.getMetodoEnvio() != null) {
            boletaDTO.setTipoEnvio(boleta.getMetodoEnvio().getTipoEnvio());
        }
        //boletaDTO.setProductos(boleta.getProductos());

    /*
    if(boleta.getProductos() != null) {
        
    }
    */
    
    return boletaDTO;
    }
}
