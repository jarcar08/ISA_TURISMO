package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Clima;
import java.util.List;


@Repository("climarepository")
public interface ClimaRepository extends JpaRepository<Clima, Integer> {
	Clima findTopByEstacion_EstIdOrderByClifechaDesc(int estId);
	List<Clima> findByCliEstadoContainingIgnoreCase(String cliEstado);
}