package com.example.demo.service.impl;

import com.example.demo.service.TipoPasajeroService;
import java.util.List;
import com.example.demo.entity.TipoPasajero;
import com.example.demo.repository.TipoPasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("tipopasajeroservice")
public class TipoPasajeroServiceImpl implements TipoPasajeroService {
	@Autowired
	@Qualifier("tipopasajerorepository")
	private TipoPasajeroRepository tipopasajeroRepository;

	@Override
	public List<TipoPasajero> listaAllTipopasajeros() {
		// TODO Auto-generated method stub
		return tipopasajeroRepository.findAll();
	}

	@Override
	public TipoPasajero saveTipopasajero(TipoPasajero tipopasajero) {
		// TODO Auto-generated method stub
		return tipopasajeroRepository.save(tipopasajero);
	}

	@Override
	public void deleteTipopasajero(int id) {
		// TODO Auto-generated method stub
		tipopasajeroRepository.deleteById(id);

	}

	@Override
	public void updateTipopasajero(TipoPasajero tipopasajero) {
		// TODO Auto-generated method stub
		tipopasajeroRepository.save(tipopasajero);
	}

	@Override
	public TipoPasajero obtenerTipoPasajeroPorId(int id) {
		// TODO Auto-generated method stub
		return tipopasajeroRepository.findById(id).orElse(null);
	}

	@Override
	public List<TipoPasajero> buscarPorNombre(String valor) {
		// TODO Auto-generated method stub
		return tipopasajeroRepository.findBytpNombreContainingIgnoreCase(valor);
	}

}
