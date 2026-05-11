package com.tienda_masc.tienda_masc.DTO;

import java.util.List;
import lombok.Data;

@Data
public class EspeciesDTO {

    private Integer idEspecie;
    private String nombreEspecie;
    private List<String> nombreProductos;
}
