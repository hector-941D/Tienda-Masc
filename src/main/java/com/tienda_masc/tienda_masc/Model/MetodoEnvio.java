package com.tienda_masc.tienda_masc.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metodos_envio")
public class MetodoEnvio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoEnvio;

    @Column(name = "tipo_envio", nullable = false)
    private String tipoEnvio; // Ej: "Retiro en Tienda", "Despacho a Domicilio"

    private Integer costoEnvio; 

}