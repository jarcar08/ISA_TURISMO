package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Rol;
import com.example.demo.service.RolService;

import org.springframework.http.ResponseEntity;

import java.util.List;
@Controller
@RequestMapping("/rol")
public class RolController {
	
	    @Autowired 
	    private RolService rolService;

	    /** Carga página con modal Thymeleaf */
	    @GetMapping("/ListaRoles")
	    public ModelAndView listAll() {
	        ModelAndView mav = new ModelAndView("ListaRoles");
	        mav.addObject("Roles", rolService.listaAllRoles());
	        return mav;
	    }

	    /** Crear/actualizar via AJAX */
	    @PostMapping("/guardar")
	    @ResponseBody
	    public String guardar(@ModelAttribute Rol t) {
	        try {
	            rolService.saveRol(t);
	            return "ok";
	        } catch (Exception ex) {
	            return "error: " + ex.getMessage();
	        }
	    }

	    /** Búsqueda por ID o nombre */
	    @GetMapping("/buscar")
	    @ResponseBody
	    public List<Rol> buscar(
	         @RequestParam(name="tipo", required=false) String tipo,
	         @RequestParam(name="valor", required=false) String valor) {
	        if ("id".equalsIgnoreCase(tipo) && valor!=null && !valor.isEmpty()) {
	            return rolService.buscarPorId(Integer.parseInt(valor));
	        } else if ("tipo".equalsIgnoreCase(tipo) && valor!=null && !valor.isEmpty()) {
	            return rolService.buscarPorNombre(valor);
	        }
	        return rolService.listaAllRoles();
	    }

	    /** Cargar para editar via AJAX */
	    @GetMapping("/editar/{id}")
	    @ResponseBody
	    public ResponseEntity<Rol> editar(@PathVariable int id) {
	        Rol t = rolService.obtenerRolPorId(id);
	        return t!=null
	           ? ResponseEntity.ok(t)
	           : ResponseEntity.notFound().build();
	    }

	    /** Eliminar */
	    @DeleteMapping("/eliminar/{id}")
	    @ResponseBody
	    public ResponseEntity<Void> eliminar(@PathVariable int id) {
	        Rol t = rolService.obtenerRolPorId(id);
	        if (t!=null) {
	            rolService.deleteRol(id);
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.notFound().build();
	    }
	}


