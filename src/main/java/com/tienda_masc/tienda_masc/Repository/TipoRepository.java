package com.tienda_masc.tienda_masc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_masc.tienda_masc.Model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    
}
