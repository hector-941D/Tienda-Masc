package com.tienda_masc.tienda_masc.DTO;

import lombok.Data;

@Data
public class ProductosDTO {

    private Integer idProducto;
    private String nombreProductos;
    private Integer precio;
    private String nombreTipo;
    private String nombreMarca;
    private String nombreEspecie;
}
