package com.example.demo.controller;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.Estacion;
import com.example.demo.entity.TipoZona;
import com.example.demo.entity.ZonaTuristica;
import com.example.demo.service.EstacionService;
import com.example.demo.service.TipoZonaService;
import com.example.demo.service.ZonaTuristicaService;

@Controller
@RequestMapping("/zona")
public class ZonaTuristicaController {
	@Autowired
    private ZonaTuristicaService zonaTuristicaService;

    @Autowired
    private EstacionService estacionService;
    
    @Autowired
    private TipoZonaService tipoZonaService;
    
    @GetMapping("/ListaZonaTuristica")
    public ModelAndView listaZonasTuristicas() {
        ModelAndView mav = new ModelAndView("ListaZonaTuristica");
        mav.addObject("zonas", zonaTuristicaService.listaAllZonaTuristicas());
        mav.addObject("estaciones", estacionService.listaAllEstaciones());
        mav.addObject("tiposZona", tipoZonaService.listAllTipoZonas());
        return mav;
    }
    
    @PostMapping("/guardar")
    @ResponseBody
    public String guardarZonaTuristica(@ModelAttribute ZonaTuristica zona) {
        zonaTuristicaService.saveZonaTuristica(zona);
        return "ok";
    }
    
    @GetMapping("/editar/{id}")
	@ResponseBody
	public ResponseEntity<ZonaTuristica> editarZonaTuristica(@PathVariable("id") int id) {
    	ZonaTuristica zonaTuristica = zonaTuristicaService.obtenerPorId(id);
		if (zonaTuristica == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(zonaTuristica);
	}
    
 // Buscar zona turística por nombre
    @GetMapping("/buscar")
    @ResponseBody
    public List<ZonaTuristica> buscarZonasTuristicas(@RequestParam(name = "tipo", required = false) String tipo,
                                           @RequestParam(name = "valor", required = false) String valor) {
        if ("nombre".equalsIgnoreCase(tipo) && valor != null && !valor.isEmpty()) {
            return zonaTuristicaService.buscarPorNombre(valor);
        }
        return zonaTuristicaService.listaAllZonaTuristicas();
    }
    
    @DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Void> eliminarPersona(@PathVariable int id) {
    	ZonaTuristica zonaTuristica = zonaTuristicaService.obtenerPorId(id);
		if (zonaTuristica != null) {
			zonaTuristicaService.deleteZonaTuristica(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
    
 // Binders para convertir ID a objetos relacionados
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TipoZona.class, "tipozona", (PropertyEditor) new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    TipoZona tz = new TipoZona();
                    tz.setTzId(Integer.parseInt(text));
                    setValue(tz);
                }
            }
        });

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