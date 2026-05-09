package com.tienda_masc.tienda_masc.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoletaDTO {

    private Integer idBoleta;
    private String numeroFolio;
    private LocalDate fechaEmision;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaEmision;
    private Integer montoNeto;
    private Integer montoIva;
    private Integer montoTotal;
    private Integer idCliente;
    private String metodoPago;
    private String tipoEnvio;
    private List<ProductosDTO> productos; 

}
