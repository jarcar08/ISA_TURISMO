package com.example.demo.security;

import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UsuarioDetalle implements UserDetails {
	private final Usuario usuario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(usuario.getRol().getRolNombre()) // Asegúrate de que Rol
																									// tenga el atributo
																									// 'rolNombre'
		);
	}

	@Override
	public String getPassword() {
		return usuario.getUsuContrasenia();
	}

	@Override
	public String getUsername() {
		return usuario.getUsuNombre(); // Usamos el nombre de usuario
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Puedes personalizar esto si usas expiración
	}

	@Override
	public boolean isAccountNonLocked() {
		return usuario.getUsuEstado() == 1; // Solo permite si estado es 1 (activo)
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return usuario.getUsuEstado() == 1;
	}

}
