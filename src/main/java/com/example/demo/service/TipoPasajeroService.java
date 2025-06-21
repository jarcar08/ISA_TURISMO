package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TipoPasajero;

public interface TipoPasajeroService {
	List<TipoPasajero> listaAllTipopasajeros();

	TipoPasajero saveTipopasajero(TipoPasajero tipopasajero);

	void deleteTipopasajero(int id);

	void updateTipopasajero(TipoPasajero tipopasajero);

	TipoPasajero obtenerTipoPasajeroPorId(int id);

	List<TipoPasajero> buscarPorNombre(String valor);

}
