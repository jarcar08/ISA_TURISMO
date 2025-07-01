package com.example.demo.service.impl;

import com.example.demo.entity.Clima;
import com.example.demo.repository.ClimaRepository;
import com.example.demo.service.ClimaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("climaservice")
public class ClimaServiceImpl implements ClimaService {
	@Autowired
    @Qualifier("climarepository")
    private ClimaRepository climaRepository;
	
	@Override
	public List<Clima> listaAllClimas() {
		return climaRepository.findAll();
	}

	@Override
	@Transactional
	public Clima saveClima(Clima clima) {
		return climaRepository.save(clima);
	}

	@Override
	public void deleteClima(int id) {
		climaRepository.deleteById(id);
	}

	@Override
	public void updateClima(Clima clima) {
		climaRepository.save(clima);
	}

	@Override
	public Clima obtenerClimaPorId(int id) {
		return climaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Clima> buscarPorEstado(String estado) {
		return climaRepository.findByCliEstadoContainingIgnoreCase(estado);
	}
}