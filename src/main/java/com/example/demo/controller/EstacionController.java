package com.example.demo.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.Estacion;
import com.example.demo.service.EstacionService;

@Controller
@RequestMapping("/estacion")
public class EstacionController {
	@Autowired
    @Qualifier("estacionservice")
    private EstacionService estacionService;
	
	@GetMapping("/ListaEstacion")
    public ModelAndView listarEstaciones() {
        ModelAndView mav = new ModelAndView("ListaEstacion");
        mav.addObject("estaciones", estacionService.listaAllEstaciones());
        return mav;
    }
	
	@PostMapping("/guardar")
    @ResponseBody
    public String guardarEstacion(@ModelAttribute Estacion estacion) {
        estacionService.saveEstacion(estacion);
        return "ok";
    }
	
	@GetMapping("/buscar")
    @ResponseBody
    public List<Estacion> buscarEstaciones(@RequestParam(name = "valor", required = false) String valor) {
        if (valor != null && !valor.isEmpty()) {
            return estacionService.buscarPorNombre(valor);
        }
        return estacionService.listaAllEstaciones();
    }
	
	@GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Estacion> editarEstacion(@PathVariable("id") int id) {
        Estacion estacion = estacionService.obtenerEstacionPorId(id);
        return estacion != null ? ResponseEntity.ok(estacion) : ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEstacion(@PathVariable int id) {
        Estacion estacion = estacionService.obtenerEstacionPorId(id);
        if (estacion != null) {
            estacionService.deleteEstacion(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Estacion.class, "estacion", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String text) {
	            if (text == null || text.isEmpty()) {
	                setValue(null);
	            } else {
	                Estacion est = new Estacion();
	                est.setEstId(Integer.parseInt(text));
	                setValue(est);
	            }
	        }
	    });
	}
}