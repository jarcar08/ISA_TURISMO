package com.example.demo.repository;

import com.example.demo.entity.TipoZona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TipoZonaRepository extends JpaRepository<TipoZona, Integer> {
    List<TipoZona> findByTzNombreContainingIgnoreCase(String nombre);
}

