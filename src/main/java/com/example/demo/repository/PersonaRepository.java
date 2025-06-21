package com.example.demo.repository;

import com.example.demo.entity.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("personarepository")
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	List<Persona> findByperNombresContainingIgnoreCase(String nombre);
	Persona findByperDni(String valor);
	List<Persona> findByPerDniContainingIgnoreCase(String dni);


}
