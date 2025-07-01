package com.example.demo.service.impl;

import com.example.demo.entity.Tarifa;
import com.example.demo.entity.TipoPasajero;
import com.example.demo.repository.TarifaRepository;
import com.example.demo.repository.TipoPasajeroRepository;
import com.example.demo.service.TarifaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tarifaservice")
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private TipoPasajeroRepository tipoPasajeroRepository;

    @Override
    public List<Tarifa> listAllTarifas() {
        return tarifaRepository.findAll();
    }

    @Override
    public Tarifa guardar(Tarifa tarifa) {
        // Validar que se haya seleccionado un tipo de pasajero
        if (tarifa.getTipopasajero() == null || tarifa.getTipopasajero().getTpId() == 0) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de pasajero");
        }
        // Cargar la entidad TipoPasajero existente
        TipoPasajero tp = tipoPasajeroRepository.findById(tarifa.getTipopasajero().getTpId())
            .orElseThrow(() -> new IllegalArgumentException("Tipo de pasajero no encontrado"));
        tarifa.setTipopasajero(tp);
        return tarifaRepository.save(tarifa);
    }

    @Override
    public List<Tarifa> buscarPorTipoPasajero(String nombreTipo) {
        return tarifaRepository
                .findByTipopasajero_TpNombreContainingIgnoreCase(nombreTipo);
    }

    @Override
    public Tarifa obtenerPorId(int id) {
        return tarifaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        tarifaRepository.deleteById(id);
    }

	@Override
	public List<Tarifa> buscarPorId(int id) {
		return tarifaRepository.findById(id)
                .map(List::of)
                .orElse(List.of());
	}
}
