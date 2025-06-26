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

	//Encriptacion de password
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Vista para el publico
	@GetMapping("/")
	public ModelAndView homePublic() {
		return new ModelAndView("VistaPublica");
	}

	//Vista para Usuario
	@GetMapping("/homeGuest")
	public ModelAndView homeGuest() {
		return new ModelAndView("HomeGuest");
	}

	//Vista para Admin
	@GetMapping("/homeAdmin")
	public ModelAndView homeAdmin() {
		return new ModelAndView("HomeAdmin");
	}

	//Funcion para cargar modelos
	@GetMapping("/registro")
	public ModelAndView mostrarRegistro() {
		ModelAndView mav = new ModelAndView("registroUnificado");
		mav.addObject("persona", new Persona());
		mav.addObject("usuario", new Usuario());
		mav.addObject("listaPasajero", tipoPasajeroService.listaAllTipopasajeros());
		return mav;
	}

	//Funcion para registrar datos
	@PostMapping("/registro")
	public String procesarRegistro(@ModelAttribute("persona") Persona persona,
			@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,  RedirectAttributes redirectAttributes) {

		System.out.println(">>> Registro recibido: " + persona.getPerNombres());

		// 1. Guardar Persona
		Persona personaGuardada = personaService.savePersona(persona);

		// 2. Configurar y guardar Usuario
		usuario.setPersona(personaGuardada);
		usuario.setUsuNombre(personaGuardada.getPerCorreo());

		// ✅ Recuperar contraseña
		String pass = request.getParameter("usuario.usuContrasenia").trim();
		System.out.println("⚠ Contraseña ingresada:" + pass);
		
		//validar, encriptar y enviar a bd
		if (pass != null && !pass.isEmpty()) {	
			usuario.setUsuContrasenia(passwordEncoder.encode(pass));

		} else {
			System.out.println("⚠ Contraseña no proporcionada.");
			return "redirect:/registro?error=password";
		}
		
		//rol y estado
		usuario.setRol(rolService.obtenerPorNombre("GUEST"));
		usuario.setUsuEstado(1);
		
		//guardar usuario en bd
		usuarioService.saveUser(usuario);
		//mensajes en consola de confirmacion
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
	
	//vista de Error
	@GetMapping("/error/403")
    public String error403() {
        return "error/403";
    }

}