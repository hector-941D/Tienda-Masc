package com.tienda_masc.tienda_masc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_masc.tienda_masc.Model.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByTipoNombreDelTipo(String nombreDelTipo);
}
     