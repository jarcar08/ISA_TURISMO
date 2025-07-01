package com.example.demo.service.impl;

import com.example.demo.entity.Horario;
import com.example.demo.repository.HorarioRepository;
import com.example.demo.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("horarioservice")
public class HorarioServiceImpl implements HorarioService{
	@Autowired
    @Qualifier("horariorepository")
    private HorarioRepository horarioRepository;

	@Override
	public List<Horario> listaAllHorarios() {
		// TODO Auto-generated method stub
		return horarioRepository.findAll();
	}

	@Override
	public Horario saveHorario(Horario Horario) {
		// TODO Auto-generated method stub
		return horarioRepository.save(Horario);
	}

	@Override
	public void deleteHorario(int id) {
		// TODO Auto-generated method stub
		horarioRepository.deleteById(id);
	}

	@Override
	public void updateHorario(Horario Horario) {
		// TODO Auto-generated method stub
		horarioRepository.save(Horario);
	}

	@Override
	public Horario obtenerHorarioPorId(int id) {
		// TODO Auto-generated method stub
		return horarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<Horario> buscarPorNombre(String valor) {
		// TODO Auto-generated method stub
		return horarioRepository.findByEstacionEstNombreContainingIgnoreCase(valor);
	}

}
