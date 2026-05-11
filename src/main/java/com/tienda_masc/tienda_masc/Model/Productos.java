package com.tienda_masc.tienda_masc.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table (name = "Productos")
public class Productos {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idProductos;

    @Column (name = "nombre_productos", nullable = false)
    private String nombreProductos;

    @NotNull
    @Column (name = "precio", nullable = false)
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especies especie;
}
