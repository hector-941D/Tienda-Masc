package com.tienda_masc.tienda_masc.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @PrePersist
    protected void crearFecha() {
        this.fechaEmision = LocalDate.now();
        this.horaEmision = LocalTime.now();
    }

    @NotNull(message = "El neto es obligatorio")
    @Column(name = "monto_neto", nullable = false)
    private Integer montoNeto;

    @Column(name = "monto_iva", nullable = false)
    private Integer montoIva;

    @Column(name = "monto_total", nullable = false)
    private Integer montoTotal;

    public enum MetodoPago {
        EFECTIVO, DEBITO, CREDITO, TRANSFERENCIA
    }

    @NotNull(message = "El método de pago es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 20)
    private MetodoPago metodoPago;

}
