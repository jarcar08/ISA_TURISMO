package com.example.demo.repository;

import com.example.demo.entity.TipoPasajero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tipopasajerorepository")
public interface TipoPasajeroRepository extends JpaRepository<TipoPasajero, Integer> {
	List<TipoPasajero> findBytpNombreContainingIgnoreCase(String nombre);

}
