package com.tienda_masc.tienda_masc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "Tipo")
@Table (id = "id")
public class Tipo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @NotBlank (message = "El tipo de producto tiene que ser seleccionado")
    @Size (min = 3, max = 100, message = "El nombre de la marca tiene que tener de 2 a 100 caracteres")
    private String nombreDelTipo;
}
