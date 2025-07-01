package com.example.demo.controller;

import com.example.demo.entity.TipoZona;
import com.example.demo.service.TipoZonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/tipozonas")
public class TipoZonaController {

    @Autowired
    private TipoZonaService tipoZonaService;

    /** Página principal con Thymeleaf + modal */
    @GetMapping("/ListaTipoZonas")
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("ListaTipoZonas");
        mav.addObject("tipozonas", tipoZonaService.listAllTipoZonas());
        mav.addObject("tipoZona", new TipoZona());
        return mav;
    }

    /** Crear / actualizar vía AJAX */
    @PostMapping("/guardar")
    @ResponseBody
    public String guardar(@ModelAttribute TipoZona tz) {
        try {
            tipoZonaService.guardar(tz);
            return "ok";
        } catch (Exception ex) {
            return "error: " + ex.getMessage();
        }
    }

    /** Búsqueda dinámica por ID o nombre */
    @GetMapping("/buscar")
    @ResponseBody
    public List<TipoZona> buscar(
            @RequestParam(name="tipo", required=false) String tipo,
            @RequestParam(name="valor", required=false) String valor) {

        if ("id".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return tipoZonaService.buscarPorId(Integer.parseInt(valor));
        } else if ("tipo".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return tipoZonaService.buscarPorNombre(valor);
        }
        return tipoZonaService.listAllTipoZonas();
    }

    /** Cargar para editar vía AJAX */
    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<TipoZona> editar(@PathVariable int id) {
        TipoZona tz = tipoZonaService.obtenerPorId(id);
        return tz != null
             ? ResponseEntity.ok(tz)
             : ResponseEntity.notFound().build();
    }

    /** Eliminar */
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        TipoZona tz = tipoZonaService.obtenerPorId(id);
        if (tz != null) {
            tipoZonaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
