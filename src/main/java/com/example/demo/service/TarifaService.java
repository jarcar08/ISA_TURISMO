package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Tarifa;

public interface TarifaService {

	List<Tarifa> listAllTarifas();
	Tarifa guardar(Tarifa tarifa);
	Tarifa obtenerPorId(int id);
	void eliminar(int id);
	List<Tarifa> buscarPorTipoPasajero(String valor);
	List<Tarifa> buscarPorId(int id);
	
}
