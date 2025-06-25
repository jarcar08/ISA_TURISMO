package com.example.demo.service.impl;

import com.example.demo.service.UsuarioService;

import jakarta.transaction.Transactional;

import java.util.List;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service("usuarioservice")
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	@Qualifier("usuariorepository")
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> listaAllUser() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional
	public Usuario saveUser(Usuario usuario) {
		// TODO Auto-generated method stub
		String hash = passwordEncoder.encode(usuario.getUsuContrasenia());
		usuario.setUsuContrasenia(hash);
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);

	}

	@Override
	public void updateUser(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario obtenerUserPorId(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario obtenerPorNombreUsuario(String correo) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsuNombre(correo);
	}

	@Override
	public List<Usuario> buscarPorNombre(String valor) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByusuNombreContainingIgnoreCase(valor);
	}

}
