package com.example.demo.repository;

import com.example.demo.entity.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rolrepository")
public interface RolRepository extends JpaRepository<Rol, Integer> {
	List<Rol> findByrolNombreContainingIgnoreCase(String nombre);
	Rol findByRolNombre(String rolNombre);

}
