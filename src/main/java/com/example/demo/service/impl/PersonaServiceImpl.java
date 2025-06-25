package com.example.demo.service.impl;

import java.util.List;
import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.PersonaService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("personaservice")
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	@Qualifier("personarepository")
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> listaAllPersonas() {
		// TODO Auto-generated method stub
		return personaRepository.findAll();
	}

	@Override
	@Transactional
	public Persona savePersona(Persona persona) {
		// TODO Auto-generated method stub
		return personaRepository.save(persona);
	}

	@Override
	public void deletePersona(int id) {
		// TODO Auto-generated method stub
		personaRepository.deleteById(id);

	}

	@Override
	public void updatePersona(Persona persona) {
		// TODO Auto-generated method stub
		personaRepository.save(persona);

	}

	@Override
	public Persona obtenerPersonaPorId(int id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Persona> buscarPorNombre(String valor) {
		// TODO Auto-generated method stub
		return personaRepository.findByperNombresContainingIgnoreCase(valor);
	}

	@Override
	public Persona BuscarPorDni(String Dni) {
		// TODO Auto-generated method stub
		return personaRepository.findByperDni(Dni);
	}

	@Override
	public List<Persona> buscarPorDniParcial(String dni) {
		// TODO Auto-generated method stub
		return personaRepository.findByPerDniContainingIgnoreCase(dni);
	}

}
