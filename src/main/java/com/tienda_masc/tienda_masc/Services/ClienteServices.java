package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.BoletaDTO;
import com.tienda_masc.tienda_masc.DTO.ClienteDTO;
import com.tienda_masc.tienda_masc.Model.Boleta;
import com.tienda_masc.tienda_masc.Model.Cliente;
import com.tienda_masc.tienda_masc.Repository.ClienteRepository;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<ClienteDTO> obtenerTodas(){
        List<ClienteDTO> clientes = new ArrayList<>();
        for(Cliente cliente : clienteRepository.findAll()){
            clientes.add(convertirADTO(cliente));
        }
        return clientes;
    }

    public BoletaDTO buscarPorId(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrada"));
        return convertirADTO(cliente);
    }


    private BoletaDTO convertirADTO (Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNombreCliente(cliente.getNombreCliente());
        clienteDTO.setRunCliente(cliente.getRunCliente());
       
        return convertirADTO(cliente);
    }
}
