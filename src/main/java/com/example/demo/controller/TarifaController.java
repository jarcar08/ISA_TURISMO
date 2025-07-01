package com.example.demo.controller;

import com.example.demo.entity.Tarifa;
import com.example.demo.entity.TipoPasajero;
import com.example.demo.service.TarifaService;
import com.example.demo.service.TipoPasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @Autowired
    private TipoPasajeroService tipoPasajeroService;

    // --- Para listar la página principal con Thymeleaf + modal ---
    @GetMapping("/ListaTarifas")
    public ModelAndView listAllTarifas() {
        ModelAndView mav = new ModelAndView("ListaTarifas");
        mav.addObject("tarifas", tarifaService.listAllTarifas());
        mav.addObject("tipopasajero", tipoPasajeroService.listaAllTipopasajeros());
        mav.addObject("tarifa", new Tarifa());
        return mav;
    }

    // --- Guardar / crear / actualizar vía AJAX (devuelve "ok" o "error:...") ---
    @PostMapping("/guardar")
    @ResponseBody
    public String guardarTarifa(@ModelAttribute Tarifa tarifa) {
        try {
            tarifaService.guardar(tarifa);
            return "ok";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // --- Endpoint REST para búsqueda dinámica ---
    @GetMapping("/buscar")
    @ResponseBody
    public List<Tarifa> buscarTarifas(
            @RequestParam(name="tipo", required=false) String tipo,
            @RequestParam(name="valor", required=false) String valor) {

        if ("id".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            int id = Integer.parseInt(valor);
            return tarifaService.buscarPorId(id);
        } else if ("tipo".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return tarifaService.buscarPorTipoPasajero(valor);
        }
        return tarifaService.listAllTarifas();
    }


    // --- Cargar una tarifa para editar vía AJAX ---
    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Tarifa> editarTarifa(@PathVariable("id") int id) {
        Tarifa t = tarifaService.obtenerPorId(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    // --- Eliminar ---
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarTarifa(@PathVariable("id") int id) {
        Tarifa t = tarifaService.obtenerPorId(id);
        if (t != null) {
            tarifaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --- InitBinder para convertir String→TipoPasajero y permitir valor "" en select ---
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(
            TipoPasajero.class,
            "tipopasajero",            // nombre de la propiedad en Tarifa
            new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) {
                    if (text == null || text.isEmpty()) {
                        // deja la propiedad a null si no se ha seleccionado nada
                        setValue(null);
                    } else {
                        TipoPasajero tp = new TipoPasajero();
                        tp.setTpId(Integer.parseInt(text));
                        setValue(tp);
                    }
                }
            }
        );
    }
}
