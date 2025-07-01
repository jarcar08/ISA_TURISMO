package com.example.demo.controller;

import com.example.demo.entity.Tren;
import com.example.demo.entity.Estado;
import com.example.demo.service.TrenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/trenes")
public class TrenController {

    @Autowired private TrenService trenService;

    /** Carga página con modal Thymeleaf */
    @GetMapping("/ListaTrenes")
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("ListaTrenes");
        mav.addObject("trenes", trenService.listAllTrenes());
        mav.addObject("tren", new Tren());
        mav.addObject("estados", Estado.values());
        return mav;
    }

    /** Crear/actualizar via AJAX */
    @PostMapping("/guardar")
    @ResponseBody
    public String guardar(@ModelAttribute Tren t) {
        try {
            trenService.guardar(t);
            return "ok";
        } catch (Exception ex) {
            return "error: " + ex.getMessage();
        }
    }

    /** Búsqueda por ID o nombre */
    @GetMapping("/buscar")
    @ResponseBody
    public List<Tren> buscar(
         @RequestParam(name="tipo", required=false) String tipo,
         @RequestParam(name="valor", required=false) String valor) {
        if ("id".equalsIgnoreCase(tipo) && valor!=null && !valor.isEmpty()) {
            return trenService.buscarPorId(Integer.parseInt(valor));
        } else if ("tipo".equalsIgnoreCase(tipo) && valor!=null && !valor.isEmpty()) {
            return trenService.buscarPorNombre(valor);
        }
        return trenService.listAllTrenes();
    }

    /** Cargar para editar via AJAX */
    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Tren> editar(@PathVariable int id) {
        Tren t = trenService.obtenerPorId(id);
        return t!=null
           ? ResponseEntity.ok(t)
           : ResponseEntity.notFound().build();
    }

    /** Eliminar */
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        Tren t = trenService.obtenerPorId(id);
        if (t!=null) {
            trenService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
