package com.example.demo.controller;

import com.example.demo.entity.TipoPasajero;
import com.example.demo.service.TipoPasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/tipopasajero")
public class TipoPasajeroController {
	
	@Autowired
	@Qualifier("tipopasajeroservice")
	private TipoPasajeroService tipopasajeroService;

	// Para Guardar
	@PostMapping("/guardar")
    @ResponseBody
    public String guardarTipoPasajero(@ModelAttribute TipoPasajero tipoPasajero) {
        tipopasajeroService.saveTipopasajero(tipoPasajero);
        return "ok";
    }

	// Para Buscar por nombre
	 // Buscar por nombre
    @GetMapping("/buscar")
    @ResponseBody
    public List<TipoPasajero> buscarTipoPasajeros(
            @RequestParam(name = "tipo", required = false) String tipo,
            @RequestParam(name = "valor", required = false) String valor) {

        if ("nombre".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return tipopasajeroService.buscarPorNombre(valor);
        }

        return tipopasajeroService.listaAllTipopasajeros();
    }

	// Para editar tipoPasajero
    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<TipoPasajero> editarTipoPasajero(@PathVariable("id") int id) {
        TipoPasajero tipoPasajero = tipopasajeroService.obtenerTipoPasajeroPorId(id);
        if (tipoPasajero == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoPasajero);
    }


	//Mostrar tipoPasajeros
	@GetMapping("ListaTipoPasajero")
	public ModelAndView listAlltipoPasajeros() {
		ModelAndView mav = new ModelAndView("ListaTipoPasajero");
		mav.addObject("ListaTipoPasajero", tipopasajeroService.listaAllTipopasajeros());
		return mav;
	}


    // Eliminar
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarTipoPasajero(@PathVariable int id) {
        TipoPasajero tipoPasajero = tipopasajeroService.obtenerTipoPasajeroPorId(id);
        if (tipoPasajero != null) {
            tipopasajeroService.deleteTipopasajero(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
