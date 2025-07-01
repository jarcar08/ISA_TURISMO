package com.example.demo.repository;

import com.example.demo.entity.Tren;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrenRepository extends JpaRepository<Tren, Integer> {
    List<Tren> findByTrenNombreContainingIgnoreCase(String nombre);
}
