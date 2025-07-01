package com.example.demo.controller;

import com.example.demo.service.EstacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MapaController {

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/mapa")
    public String mostrarMapa(Model model) {
        List<Map<String, Object>> estaciones = estacionService.listarEstacionesConClimaYZonas();
        System.out.println("Estaciones cargadas: " + estaciones.size()); // ← Agrega esto temporalmente
        model.addAttribute("estaciones", estaciones);
        return "mapa";
    }


}