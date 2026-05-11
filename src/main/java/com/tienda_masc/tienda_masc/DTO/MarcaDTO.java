package com.tienda_masc.tienda_masc.DTO;

import java.util.List;
import lombok.Data;

@Data
public class MarcaDTO {

    private Integer idMarca;
    private String nombreMarca;
    private List<String> nombreProductos;
}
