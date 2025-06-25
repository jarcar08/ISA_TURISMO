package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;
import com.example.demo.service.PersonaService;
import com.example.demo.service.RolService;
import com.example.demo.service.TipoPasajeroService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping
public class RutaController {

	@Autowired
	@Qualifier("usuarioservice")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("tipopasajeroservice")
	private TipoPasajeroService tipoPasajeroService;

	@Autowired
	@Qualifier("personaservice")
	private PersonaService personaService;

	@Autowired
	@Qualifier("rolservice")
	private RolService rolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Vista para el publico
	@GetMapping("/")
	public ModelAndView homePublic() {
		return new ModelAndView("VistaPublica");
	}

	@GetMapping("/homeGuest")
	public ModelAndView homeGuest() {
		return new ModelAndView("HomeGuest"); // Crea esta vista .html
	}

	@GetMapping("/homeAdmin")
	public ModelAndView homeAdmin() {
		return new ModelAndView("HomeAdmin"); // Asegúrate de tener HomeAdmin.html
	}

	@GetMapping("/registro")
	public ModelAndView mostrarRegistro() {
		ModelAndView mav = new ModelAndView("registroUnificado");
		mav.addObject("persona", new Persona());
		mav.addObject("usuario", new Usuario());
		mav.addObject("listaPasajero", tipoPasajeroService.listaAllTipopasajeros());// 👈 este es clave
		return mav;
	}

	@PostMapping("/registro")
	public String procesarRegistro(@ModelAttribute("persona") Persona persona,
			@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,  RedirectAttributes redirectAttributes) {

		System.out.println(">>> Registro recibido: " + persona.getPerNombres());

		// 1. Guardar Persona
		Persona personaGuardada = personaService.savePersona(persona);

		// 2. Configurar y guardar Usuario
		usuario.setPersona(personaGuardada);
		usuario.setUsuNombre(personaGuardada.getPerCorreo());

		// ✅ Recuperar y encriptar contraseña desde el formulario
		String rawPassword = request.getParameter("usuario.usuContrasenia");
		if (rawPassword != null && !rawPassword.isEmpty()) {
			usuario.setUsuContrasenia(passwordEncoder.encode(rawPassword));
		} else {
			System.out.println("⚠ Contraseña no proporcionada.");
			return "redirect:/registro?error=password"; // puedes manejar esto en el frontend
		}

		usuario.setRol(rolService.obtenerPorNombre("GUEST")); // Rol por defecto
		usuario.setUsuEstado(1);

		usuarioService.saveUser(usuario);

		System.out.println("Persona: " + persona.getPerCorreo());
		System.out.println("Usuario registrado con éxito!");

		redirectAttributes.addFlashAttribute("success", "Registro exitoso. Por favor, inicia sesión.");
		return "redirect:/login";
	}

	// Vista de login
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("Login");
	}

}
