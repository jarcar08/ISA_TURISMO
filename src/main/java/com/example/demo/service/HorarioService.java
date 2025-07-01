package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Horario;

public interface HorarioService {

	List<Horario> listaAllHorarios();

	Horario saveHorario(Horario Horario);

	void deleteHorario(int id);

	void updateHorario(Horario Horario); // nuevo
	
	Horario obtenerHorarioPorId(int id);
	
	List<Horario> buscarPorNombre(String valor);
}
