package com.example.demo.security;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetalleService implements UserDetailsService {
	private final UsuarioRepository usuarioRepository;
	private static final Logger log = LoggerFactory.getLogger(UsuarioDetalleService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("🔍 Buscando usuario con nombre: {}", username);
		Usuario usuario = usuarioRepository.findByUsuNombre(username);
		if (usuario == null) {
			log.warn("⚠ Usuario no encontrado: {}", username);
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		log.info("✅ Usuario encontrado: {}, Rol: {}", usuario.getUsuNombre(), usuario.getRol().getRolNombre());
		return new UsuarioDetalle(usuario);
	}

}
