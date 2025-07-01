package com.example.demo.controller;

import com.example.demo.entity.Horario;
import com.example.demo.service.EstacionService;
import com.example.demo.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/horario")
public class HorarioController {

		@Autowired
		@Qualifier("horarioservice")
		private HorarioService horarioService;
		
		@Autowired
		@Qualifier("estacionservice")
		private EstacionService estacionService;

		@PostMapping("/guardar")
		@ResponseBody
		public String guardarHorario(@ModelAttribute Horario Horario) {			
			horarioService.saveHorario(Horario);
			return "ok";
		}
		
		
		//para buscar por estacion en horario
		@GetMapping("/buscar")
		@ResponseBody
		public List<Horario> buscarHorarios(
		    @RequestParam(name = "tipo", required = false) String tipo,
		    @RequestParam(name = "valor", required = false) String valor) {

		    if ("nombreEstacion".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
		        return horarioService.buscarPorNombre(valor); // Usa el SP aquí
		    }

		    return horarioService.listaAllHorarios();
		}


		// Para editar Horario
		@GetMapping("/editar/{id}")
		@ResponseBody
		public ResponseEntity<Horario> editarHorario(@PathVariable("id") int id) {
			Horario Horario = horarioService.obtenerHorarioPorId(id);
			if (Horario == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(Horario);
		}

		// Listar Horarios
		@GetMapping("ListaHorario")
		public ModelAndView listAllHorarios() {
			ModelAndView mav = new ModelAndView("ListaHorario");
			mav.addObject("Horario", horarioService.listaAllHorarios());
			mav.addObject("listaEstacion", estacionService.listaAllEstaciones());		
			return mav;
		}

		@DeleteMapping("/eliminar/{id}")
		@ResponseBody
		public ResponseEntity<Void> eliminarHorario(@PathVariable int id) {
			Horario Horario = horarioService.obtenerHorarioPorId(id);
			if (Horario != null) {
				horarioService.deleteHorario(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		}

	}
