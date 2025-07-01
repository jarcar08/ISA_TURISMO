package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ZonaTuristica;
@Repository("zonaturisticarepository")
public interface ZonaTuristicaRepository extends JpaRepository<ZonaTuristica, Integer> {
	List<ZonaTuristica> findByEstacion_EstId(int estId);
	List<ZonaTuristica> findByzotNombreContainingIgnoreCase(String nombre);
}