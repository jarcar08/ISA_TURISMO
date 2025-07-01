package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ZonaTuristica;
import com.example.demo.repository.ZonaTuristicaRepository;
import com.example.demo.service.ZonaTuristicaService;
import jakarta.transaction.Transactional;

@Service("zonaturisticaservice")
public class ZonaTuristicaServiceImpl implements ZonaTuristicaService{
	@Autowired
	@Qualifier("zonaturisticarepository")
	private ZonaTuristicaRepository zonaTuristicaRepository;
	
	@Override
	public List<ZonaTuristica> listaAllZonaTuristicas() {
		return zonaTuristicaRepository.findAll();
	}

	@Override
	@Transactional
	public ZonaTuristica saveZonaTuristica(ZonaTuristica zona) {
		return zonaTuristicaRepository.save(zona);
	}

	@Override
	public void deleteZonaTuristica(int id) {
		zonaTuristicaRepository.deleteById(id);
	}

	@Override
	public void updateZonaTuristica(ZonaTuristica zona) {
		zonaTuristicaRepository.save(zona);
	}

	@Override
	public ZonaTuristica obtenerPorId(int id) {
		return zonaTuristicaRepository.findById(id).orElse(null);
	}

	@Override
	public List<ZonaTuristica> buscarPorNombre(String valor) {
		return zonaTuristicaRepository.findByzotNombreContainingIgnoreCase(valor);
	}
}