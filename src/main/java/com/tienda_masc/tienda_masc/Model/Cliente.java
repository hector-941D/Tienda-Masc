package com.tienda_masc.tienda_masc.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;
    
    @Column(name = "run_cliente", nullable = false, unique = true)
    private String runCliente;

    @OneToMany(mappedBy = "cliente") 
    private List<Boleta> boletas;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

}
