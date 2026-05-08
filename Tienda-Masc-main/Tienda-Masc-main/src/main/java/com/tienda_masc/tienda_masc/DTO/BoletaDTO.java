package com.tienda_masc.tienda_masc.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class BoletaDTO {

    private Integer idBoleta;
    private String numeroFolio;
    private LocalDate fechaEmision;
    private LocalTime horaEmision;
    private Integer montoNeto;
    private Integer montoIva;
    private Integer montoTotal;
    private String metodoPago;

}
