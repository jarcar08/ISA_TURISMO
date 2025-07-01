package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Horario;

@Repository("horariorepository")
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
	List<Horario> findByEstacionEstNombreContainingIgnoreCase(String nombre);


}






	