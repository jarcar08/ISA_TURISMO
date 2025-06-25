package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usuariorepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByUsuNombre(String usuNombre);
	List<Usuario> findByusuNombreContainingIgnoreCase(String nombre);

}
