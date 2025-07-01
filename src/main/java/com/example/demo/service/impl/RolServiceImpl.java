package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.entity.Rol;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("rolservice")
public class RolServiceImpl implements RolService {
	@Autowired
	@Qualifier("rolrepository")
	private RolRepository rolRepository;

	@Override
	public List<Rol> listaAllRoles() {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}

	@Override
	public Rol saveRol(Rol rol) {
		// TODO Auto-generated method stub
		return rolRepository.save(rol);
	}

	@Override
	public void deleteRol(int id) {
		// TODO Auto-generated method stub
		rolRepository.deleteById(id);
	}

	@Override
	public void updateRol(Rol rol) {
		// TODO Auto-generated method stub
		rolRepository.save(rol);
	}

	@Override
	public Rol obtenerRolPorId(int id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public List<Rol> buscarPorNombre(String valor) {
		// TODO Auto-generated method stub
		return rolRepository.findByrolNombreContainingIgnoreCase(valor);
	}

	@Override
	public Rol obtenerPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return rolRepository.findByRolNombre(nombre);
	}

	@Override
	public List<Rol> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id).map(List::of).orElse(List.of());
	}

}
