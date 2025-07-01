package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ZonaTuristica;

public interface ZonaTuristicaService {
	List<ZonaTuristica> listaAllZonaTuristicas();
    ZonaTuristica saveZonaTuristica(ZonaTuristica zona);
    void deleteZonaTuristica(int id);
	void updateZonaTuristica(ZonaTuristica zona);
    ZonaTuristica obtenerPorId(int id);
    List<ZonaTuristica> buscarPorNombre(String valor);
}