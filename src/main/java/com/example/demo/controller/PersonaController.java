package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPasajero;
import com.example.demo.service.PersonaService;
import com.example.demo.service.TipoPasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	@Qualifier("personaservice")
	private PersonaService personaService;

	@Autowired
	@Qualifier("tipopasajeroservice")
	private TipoPasajeroService tipopasajeroService;

	// Para Guardar
	@PostMapping("/guardar")
	@ResponseBody
	public String guardarPersona(@ModelAttribute Persona persona) {
		Persona existente = personaService.BuscarPorDni(persona.getPerDni());
		if (existente != null && (persona.getPerId() == 0 || persona.getPerId() != existente.getPerId())) {
			return "error: El DNI ya está registrado";
		}

		personaService.savePersona(persona);
		return "ok";
	}

	// Para Buscar por nombre
	@GetMapping("/buscar")
	@ResponseBody
	public List<Persona> buscarPersonas(@RequestParam(name = "tipo", required = false) String tipo,
			@RequestParam(name = "valor", required = false) String valor) {
		if ("nombre".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
			return personaService.buscarPorNombre(valor);
		} else if ("dni".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
			return personaService.buscarPorDniParcial(valor);
		}
		return personaService.listaAllPersonas();
	}

	/*
	 * @GetMapping("/buscar")
	 * 
	 * @ResponseBody public List<Persona> buscarPersonas(@RequestParam(name =
	 * "tipo", required = false) String tipo,
	 * 
	 * @RequestParam(name = "valor", required = false) String valor) { if
	 * ("nombre".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
	 * return personaService.buscarPorNombre(valor); } return
	 * personaService.listaAllPersonas(); }
	 */

	// Para editar Persona
	@GetMapping("/editar/{id}")
	@ResponseBody
	public ResponseEntity<Persona> editarPersona(@PathVariable("id") int id) {
		Persona persona = personaService.obtenerPersonaPorId(id);
		if (persona == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(persona);
	}


	//Mostrar Personas
	@GetMapping("ListaPersona")
	public ModelAndView listAllPersonas() {
		ModelAndView mav = new ModelAndView("ListaPersona");
		mav.addObject("persona", personaService.listaAllPersonas());
		mav.addObject("listaPasajero", tipopasajeroService.listaAllTipopasajeros());// 👈 este es clave
		return mav;
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Void> eliminarPersona(@PathVariable int id) {
		Persona persona = personaService.obtenerPersonaPorId(id);
		if (persona != null) {
			personaService.deletePersona(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TipoPasajero.class, "tipopasajero", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				if (text == null || text.isEmpty()) {
					setValue(null); // <- esto evita el error cuando el select es ""
				} else {
					TipoPasajero tp = new TipoPasajero();
					tp.setTpId(Integer.parseInt(text));
					setValue(tp);
				}
			}
		});
	}

}
