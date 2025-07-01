package com.example.demo.service;

import java.util.List;
import java.util.Map;
import com.example.demo.entity.Estacion;

public interface EstacionService {
	List<Estacion> listaAllEstaciones();
	List<Map<String, Object>> listarEstacionesConClimaYZonas();
	Estacion saveEstacion(Estacion estacion);
	void deleteEstacion(int id);
	void updateEstacion(Estacion estacion);
	Estacion obtenerEstacionPorId(int id);
	List<Estacion> buscarPorNombre(String valor);
}