package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Clima;

public interface ClimaService {
	List<Clima> listaAllClimas();
	Clima saveClima(Clima clima);
	void deleteClima(int id);
	void updateClima(Clima clima);
	Clima obtenerClimaPorId(int id);
	List<Clima> buscarPorEstado(String estado);
}