package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Persona;

public interface PersonaService {
	List<Persona> listaAllPersonas();

	Persona savePersona(Persona persona);

	void deletePersona(int id);

	void updatePersona(Persona persona);

	Persona obtenerPersonaPorId(int id);

	List<Persona> buscarPorNombre(String valor);

	Persona BuscarPorDni(String Dni);
	
	List<Persona> buscarPorDniParcial(String dni);

}
