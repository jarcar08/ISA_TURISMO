package com.example.demo.service;

import com.example.demo.entity.Tren;
import java.util.List;

public interface TrenService {
    List<Tren> listAllTrenes();
    Tren guardar(Tren tren);
    List<Tren> buscarPorId(int id);
    List<Tren> buscarPorNombre(String nombre);
    Tren obtenerPorId(int id);
    void eliminar(int id);
}
