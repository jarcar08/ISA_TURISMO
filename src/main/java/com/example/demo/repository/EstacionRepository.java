package com.example.demo.repository;

import com.example.demo.entity.Estacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estacionrepository")
public interface EstacionRepository extends JpaRepository<Estacion, Integer> {
	List<Estacion> findByestNombreContainingIgnoreCase(String nombre);
}