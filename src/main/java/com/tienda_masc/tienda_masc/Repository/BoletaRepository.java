package com.tienda_masc.tienda_masc.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_masc.tienda_masc.Model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
    Optional<Boleta> findFirstByOrderByIdBoletaDesc();
}

