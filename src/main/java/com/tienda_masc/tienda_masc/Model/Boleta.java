package com.tienda_masc.tienda_masc.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBoleta;

    @Column(name = "numero_folio", nullable = false, unique = true)
    private String numeroFolio;

    @Column(name = "fecha_emision", nullable = false, updatable = false)
    private LocalDate fechaEmision;

    @Column(name = "hora_emision", nullable = false, updatable = false)
    private LocalTime horaEmision;

    @NotNull(message = "El neto es obligatorio")
    @Column(name = "monto_neto", nullable = false)
    private Integer montoNeto;

    @Column(name = "monto_iva", nullable = false)
    private Integer montoIva;

    @Column(name = "monto_total", nullable = false)
    private Integer montoTotal;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_metodo_envio")
    private MetodoEnvio metodoEnvio;

    @ManyToOne 
    @JoinColumn(name = "id_cliente", nullable = false) 
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "boleta_productos",
        joinColumns = @JoinColumn(name = "id_boleta"),
        inverseJoinColumns = @JoinColumn(name = "id_productos")
    )
    private List<Productos> productos;

    @PrePersist
    protected void crearFecha() {
        this.fechaEmision = LocalDate.now();
        this.horaEmision = LocalTime.now();
    }
}
