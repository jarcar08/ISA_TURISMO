package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Usuario;

public interface UsuarioService {
	List<Usuario> listaAllUser();

	Usuario saveUser(Usuario usuario);

	void deleteUser(int id);

	void updateUser(Usuario usuario);

	Usuario obtenerUserPorId(int id);

	Usuario obtenerPorNombreUsuario(String correo);
	
	List<Usuario> buscarPorNombre(String valor);

}
