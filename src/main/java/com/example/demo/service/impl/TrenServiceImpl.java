package com.example.demo.service.impl;

import com.example.demo.entity.Tren;
import com.example.demo.repository.TrenRepository;
import com.example.demo.service.TrenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("trenservice")
public class TrenServiceImpl implements TrenService {

    @Autowired
    private TrenRepository trenRepository;

    @Override
    public List<Tren> listAllTrenes() {
        return trenRepository.findAll();
    }

    @Override
    public Tren guardar(Tren tren) {
        return trenRepository.save(tren);
    }

    @Override
    public List<Tren> buscarPorId(int id) {
        return trenRepository.findById(id)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<Tren> buscarPorNombre(String nombre) {
        return trenRepository.findByTrenNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Tren obtenerPorId(int id) {
        return trenRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        trenRepository.deleteById(id);
    }
}

