package com.example.demo.service;

import com.example.demo.entity.TipoZona;
import java.util.List;

public interface TipoZonaService {

    /** Devuelve todos los tipos de zona */
    List<TipoZona> listAllTipoZonas();

    /** Crea o actualiza un tipo de zona */
    TipoZona guardar(TipoZona tz);

    /** Busca por ID exacto */
    List<TipoZona> buscarPorId(int id);

    /** Busca por nombre parcial (ignore case) */
    List<TipoZona> buscarPorNombre(String nombre);

    /** Obtiene un tipoZona por ID o null */
    TipoZona obtenerPorId(int id);

    /** Elimina por ID */
    void eliminar(int id);
}