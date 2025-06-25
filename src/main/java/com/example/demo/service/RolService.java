package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Rol;

public interface RolService {
	List<Rol> listaAllRoles();

	Rol saveRol(Rol rol);

	void deleteRol(int id);

	void updateRol(Rol rol);

	Rol obtenerRolPorId(int id);

	List<Rol> buscarPorNombre(String valor);

	Rol obtenerPorNombre(String nombre);

}
