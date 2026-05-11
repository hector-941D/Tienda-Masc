package com.tienda_masc.tienda_masc.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "marca")
public class Marca {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    @NotBlank (message = "La marca tiene que tener nombre")
    @Size (min = 2, max = 100, message = "El nombre de la marca tiene que tener de 2 a 100 caracteres")
    private String nombreMarca;

    @OneToMany(mappedBy = "marca")
    @ToString.Exclude
    private List<Productos> productos;
}
