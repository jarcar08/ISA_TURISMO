package com.example.demo.service.impl;

import com.example.demo.entity.TipoZona;
import com.example.demo.repository.TipoZonaRepository;
import com.example.demo.service.TipoZonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tipozonaservice")
public class TipoZonaServiceImpl implements TipoZonaService {

    @Autowired
    private TipoZonaRepository tipoZonaRepository;

    @Override
    public List<TipoZona> listAllTipoZonas() {
        return tipoZonaRepository.findAll();
    }

    @Override
    public TipoZona guardar(TipoZona tz) {
        return tipoZonaRepository.save(tz);
    }

    @Override
    public List<TipoZona> buscarPorId(int id) {
        return tipoZonaRepository.findById(id)
            .map(List::of)
            .orElse(List.of());
    }

    @Override
    public List<TipoZona> buscarPorNombre(String nombre) {
        return tipoZonaRepository.findByTzNombreContainingIgnoreCase(nombre);
    }

    @Override
    public TipoZona obtenerPorId(int id) {
        return tipoZonaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        tipoZonaRepository.deleteById(id);
    }
}
