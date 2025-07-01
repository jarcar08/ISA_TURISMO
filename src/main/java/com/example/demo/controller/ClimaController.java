package com.example.demo.controller;

import com.example.demo.entity.Clima;
import com.example.demo.entity.Estacion;
import com.example.demo.service.ClimaService;
import com.example.demo.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@RequestMapping("/clima")
public class ClimaController {

    @Autowired
    @Qualifier("climaservice")
    private ClimaService climaService;

    @Autowired
    @Qualifier("estacionservice")
    private EstacionService estacionService;

    @PostMapping("/guardar")
    @ResponseBody // ← ES OBLIGATORIO para que no devuelva un redirect
    public String guardarClima(@ModelAttribute Clima clima) {
        try {
            climaService.saveClima(clima);
            return "ok";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @GetMapping("/buscar")
    @ResponseBody
    public List<Clima> buscarClima(@RequestParam(name = "tipo", required = false) String tipo,
                                   @RequestParam(name = "valor", required = false) String valor) {
        if ("estado".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return climaService.buscarPorEstado(valor);
        }
        return climaService.listaAllClimas();
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Clima> editarClima(@PathVariable("id") int id) {
        Clima clima = climaService.obtenerClimaPorId(id);
        if (clima == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clima);
    }

    @GetMapping("/ListaClima")
    public ModelAndView vistaClima() {
        ModelAndView mav = new ModelAndView("ListaClima");
        mav.addObject("climas", climaService.listaAllClimas());
        mav.addObject("estaciones", estacionService.listaAllEstaciones());
        return mav;
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarClima(@PathVariable int id) {
        Clima clima = climaService.obtenerClimaPorId(id);
        if (clima != null) {
            climaService.deleteClima(id);
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