package com.tienda_masc.tienda_masc.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda_masc.tienda_masc.DTO.ClienteDTO;
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

    public ClienteDTO guardarCliente(Cliente cliente) {
    if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
        throw new RuntimeException("El nombre del cliente es obligatorio.");
    }

    if (cliente.getRunCliente() == null || cliente.getRunCliente().trim().isEmpty()) {
        throw new RuntimeException("El RUN del cliente es obligatorio.");
    }

    Optional<Cliente> clienteExistente = clienteRepository.findByRunCliente(cliente.getRunCliente());
    if (clienteExistente.isPresent()) {
        throw new RuntimeException("Ya existe un cliente registrado con el RUN: " + cliente.getRunCliente());
    }

    cliente.setNombreCliente(cliente.getNombreCliente().toUpperCase().trim());
    cliente.setRunCliente(cliente.getRunCliente().toUpperCase().trim());


    Cliente clienteGuardado = clienteRepository.save(cliente);
    return convertirADTO(clienteGuardado);
}

    public ClienteDTO buscarPorId(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return convertirADTO(cliente);
    }


    private ClienteDTO convertirADTO (Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNombreCliente(cliente.getNombreCliente());
        clienteDTO.setRunCliente(cliente.getRunCliente());
        /*List<ProductosDTO> listaProductos = new ArrayList<>();
    
        if (cliente.getBoletas() != null) {
            for (Boleta boleta : cliente.getBoletas()) {
                if (boleta.getProductos() != null) {
                    for (Productos prod : boleta.getProductos()) {
                        listaProductos.add(convertirProductoADTO(prod));
                    }
                }
            }
        }
        
        clienteDTO.setProductosComprados(listaProductos);
        */
        return clienteDTO;
    }

    /*private ProductosDTO convertirProductoADTO(Productos producto) {
    if (producto == null) return null;
    ProductosDTO dto = new ProductosDTO();
    //dto.setIdProducto(producto.getIdProductos());
    //dto.setNombreProducto(producto.getNombreProductos());
    //dto.setPrecio(producto.getPrecio());
    
    return dto;
    */
}

