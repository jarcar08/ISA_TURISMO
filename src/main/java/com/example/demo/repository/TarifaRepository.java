package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Tarifa;

@Repository("tarifarepository")
public interface TarifaRepository extends JpaRepository<Tarifa,Integer> {

	List<Tarifa> findByTaPrecioPasaje(double precio);

	List<Tarifa> findByTipopasajero_TpNombreContainingIgnoreCase(String nombreTipo);

	
}
