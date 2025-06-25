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

	// Para Guardar
	@PostMapping("/guardar")
	@ResponseBody
	public String guardarUsuario(@ModelAttribute Usuario usuario) {
		try {
			Persona persona = personaService.obtenerPersonaPorId(usuario.getPersona().getPerId());
			if (persona == null) {
				return "error: Persona no encontrada";
			}

			usuario.setUsuNombre(persona.getPerCorreo());
			usuario.setRol(rolService.obtenerPorNombre("Guest"));
			usuario.setUsuEstado(1);
			usuario.setUsuContrasenia(passwordEncoder.encode(usuario.getUsuContrasenia()));
			usuario.setPersona(persona);
			usuarioService.saveUser(usuario);

			return "ok";
		} catch (Exception e) {
			return "error: " + e.getMessage();
		}
	}

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
