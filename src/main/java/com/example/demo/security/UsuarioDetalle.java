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
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getRolNombre()) 
		);
	}

	@Override
	public String getPassword() {
		System.out.println("getpassword: " + usuario.getUsuContrasenia());
		return usuario.getUsuContrasenia();
	}

	@Override
	public String getUsername() {
		return usuario.getUsuNombre();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return usuario.getUsuEstado() == 1;
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
