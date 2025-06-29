package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;
import com.example.demo.service.PersonaService;
import com.example.demo.service.RolService;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	@Qualifier("usuarioservice")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("personaservice")
	private PersonaService personaService;

	@Autowired
	@Qualifier("rolservice")
	private RolService rolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/registro")
	public ModelAndView mostrarFormularioRegistro(@RequestParam("personaId") int personaId) {
		Persona persona = personaService.obtenerPersonaPorId(personaId);
		ModelAndView mav = new ModelAndView("RegistroUsuario");
		mav.addObject("persona", persona);
		return mav;
	}

	// guardar Usuario
	@PostMapping("/guardar")
	@ResponseBody
	public String guardarUsuario(@ModelAttribute Usuario usuario) {
		try {
			// Buscar persona por ID
			Persona persona = personaService.obtenerPersonaPorId(usuario.getPersona().getPerId());
			if (persona == null) {
				return "error: Persona no encontrada";
			}

			// Establecer correo como nombre de usuario
			usuario.setUsuNombre(persona.getPerCorreo());
			usuario.setPersona(persona); // Relación con persona

			// Validar si ya existe un usuario con el mismo correo
			Usuario existente = usuarioService.obtenerPorNombreUsuario(usuario.getUsuNombre());
			if (existente != null && (usuario.getUsuId() == null || !usuario.getUsuId().equals(existente.getUsuId()))) {
				return "error: El correo ya está registrado";
			}

			if (usuario.getUsuId() == null) {
				// Nuevo usuario: validar y encriptar contraseña
				if (usuario.getUsuContrasenia() == null || usuario.getUsuContrasenia().isBlank()) {
					return "error: Contraseña no proporcionada";
				}
				usuario.setUsuContrasenia(passwordEncoder.encode(usuario.getUsuContrasenia()));
			} else {
				// Edición: No modificar contraseña si está vacía
				Usuario usuarioExistente = usuarioService.obtenerUserPorId(usuario.getUsuId());
				if (usuario.getUsuContrasenia() == null || usuario.getUsuContrasenia().isBlank()) {
					usuario.setUsuContrasenia(usuarioExistente.getUsuContrasenia());
				} else {
					usuario.setUsuContrasenia(passwordEncoder.encode(usuario.getUsuContrasenia()));
				}
			}

			// Validar Rol
			if (usuario.getRol() == null || usuario.getRol().getRolId() == 0) {
				return "error: Rol no seleccionado";
			}
			usuario.setRol(rolService.obtenerRolPorId(usuario.getRol().getRolId()));

			// Validar Estado
			if (usuario.getUsuEstado() == null || (usuario.getUsuEstado() != 0 && usuario.getUsuEstado() != 1)) {
				usuario.setUsuEstado(1); // Por defecto activo
			}

			// Guardar en la BD
			usuarioService.saveUser(usuario);
			return "ok";

		} catch (Exception e) {
			return "error: " + e.getMessage();
		}
	}

	/*
	 * //guardar Persona
	 * 
	 * @PostMapping("/guardar")
	 * 
	 * @ResponseBody public String guardarUsuario(@ModelAttribute Usuario usuario) {
	 * try { if (usuario.getPersona() == null || usuario.getPersona().getPerId() ==
	 * null) { return "error: Persona no seleccionada"; }
	 * 
	 * Persona persona =
	 * personaService.obtenerPersonaPorId(usuario.getPersona().getPerId()); if
	 * (persona == null) { return "error: Persona no encontrada"; }
	 * 
	 * if (usuario.getUsuId() == null) { boolean yaExiste =
	 * usuarioService.existePorPersonaId(usuario.getPersona().getPerId()); if
	 * (yaExiste) { return "error: La persona ya tiene un usuario registrado"; } }
	 * 
	 * usuario.setUsuNombre(persona.getPerCorreo()); usuario.setPersona(persona);
	 * 
	 * if (usuario.getUsuContrasenia() == null ||
	 * usuario.getUsuContrasenia().isBlank()) { return
	 * "error: Contraseña no proporcionada"; }
	 * 
	 * usuario.setUsuContrasenia(passwordEncoder.encode(usuario.getUsuContrasenia())
	 * );
	 * 
	 * if (usuario.getRol() == null || usuario.getRol().getRolId() == 0) { return
	 * "error: Rol no seleccionado"; }
	 * usuario.setRol(rolService.obtenerRolPorId(usuario.getRol().getRolId()));
	 * 
	 * if (usuario.getUsuEstado() != 0 && usuario.getUsuEstado() != 1) {
	 * usuario.setUsuEstado(1); }
	 * 
	 * usuarioService.saveUser(usuario); return "ok";
	 * 
	 * } catch (Exception e) { return "error: " + e.getMessage(); } }
	 */

	// en prueba el de arriba

	/*
	 * @PostMapping("/guardar")
	 * 
	 * @ResponseBody public String guardarUsuario(@ModelAttribute Usuario usuario) {
	 * try { // Buscar persona por ID Persona persona =
	 * personaService.obtenerPersonaPorId(usuario.getPersona().getPerId()); if
	 * (persona == null) { return "error: Persona no encontrada"; }
	 * 
	 * 
	 * // Establecer correo como nombre de usuario
	 * usuario.setUsuNombre(persona.getPerCorreo()); usuario.setPersona(persona); //
	 * Relación con persona
	 * 
	 * // Validar y encriptar contraseña if (usuario.getUsuContrasenia() == null ||
	 * usuario.getUsuContrasenia().isBlank()) { return
	 * "error: Contraseña no proporcionada"; }
	 * 
	 * // Validar Rol if (usuario.getRol() == null || usuario.getRol().getRolId() ==
	 * 0) { return "error: Rol no seleccionado"; }
	 * usuario.setRol(rolService.obtenerRolPorId(usuario.getRol().getRolId()));
	 * 
	 * // Validar Estado if (usuario.getUsuEstado() != 0 && usuario.getUsuEstado()
	 * != 1) { usuario.setUsuEstado(1); // Por defecto activo }
	 * 
	 * // Guardar en la BD usuarioService.saveUser(usuario); return "ok";
	 * 
	 * } catch (Exception e) { return "error: " + e.getMessage(); } }
	 */

	// Para Buscar por correo
	@GetMapping("/buscar")
	@ResponseBody
	public List<Usuario> buscarUsuarios(@RequestParam(name = "tipo", required = false) String tipo,
			@RequestParam(name = "valor", required = false) String valor) {
		if ("nombre".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
			return usuarioService.buscarPorNombre(valor);
		}
		return usuarioService.listaAllUser();
	}

	// Para editar Usuario
	@GetMapping("/editar/{id}")
	@ResponseBody
	public ResponseEntity<Usuario> editarUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.obtenerUserPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	// Lista Users
	@GetMapping("ListaUsuario")
	public ModelAndView listAllUsuarios() {
		ModelAndView mav = new ModelAndView("ListaUsuario");
		mav.addObject("usuario", usuarioService.listaAllUser());
		mav.addObject("listaPersona", personaService.listaAllPersonas());
		mav.addObject("listaRol", rolService.listaAllRoles());
		return mav;
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
		Usuario usuario = usuarioService.obtenerUserPorId(id);
		if (usuario != null) {
			usuarioService.deleteUser(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
