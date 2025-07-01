package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Clima;
import com.example.demo.entity.Estacion;
import com.example.demo.entity.ZonaTuristica;
import com.example.demo.repository.ClimaRepository;
import com.example.demo.repository.EstacionRepository;
import com.example.demo.repository.ZonaTuristicaRepository;
import com.example.demo.service.EstacionService;

@Service("estacionservice")
public class EstacionServiceImpl implements EstacionService{
	@Autowired
	@Qualifier("estacionrepository")
	private EstacionRepository estacionRepository;

    @Autowired
    private ClimaRepository climaRepository;

    @Autowired
    private ZonaTuristicaRepository zonaRepository;
	
	@Override
	public List<Estacion> listaAllEstaciones() {
		return estacionRepository.findAll();
	}

	@Override
	public Estacion saveEstacion(Estacion estacion) {
		return estacionRepository.save(estacion);
	}

	@Override
	public void deleteEstacion(int id) {
		estacionRepository.deleteById(id);
	}

	@Override
	public void updateEstacion(Estacion estacion) {
		estacionRepository.save(estacion);
	}

	@Override
	public Estacion obtenerEstacionPorId(int id) {
		return estacionRepository.findById(id).orElse(null);
	}

	@Override
	public List<Estacion> buscarPorNombre(String valor) {
		return estacionRepository.findByestNombreContainingIgnoreCase(valor);
	}

	@Override
	public List<Map<String, Object>> listarEstacionesConClimaYZonas() {
	    List<Estacion> estaciones = estacionRepository.findAll();
	    List<Map<String, Object>> resultado = new ArrayList<>();
	    for (Estacion est : estaciones) {
	        Map<String, Object> data = new HashMap<>();
	        data.put("id", est.getEstId());
	        data.put("numero", est.getEstNumero());
	        data.put("nombre", est.getEstNombre());
	        data.put("ubicacion", est.getEstUbicacion());
	        data.put("lat", est.getEstLat());
	        data.put("lng", est.getEstLong());
	        // Clima actual
	        Clima clima = climaRepository.findTopByEstacion_EstIdOrderByClifechaDesc(est.getEstId());
	        if (clima != null) {
	            Map<String, Object> climaMap = new HashMap<>();
	            climaMap.put("estado", clima.getCliEstado());
	            climaMap.put("tempMax", clima.getCliTempMax());
	            climaMap.put("tempMin", clima.getCliTempMin());
	            climaMap.put("recomendacion", clima.getCliRecomendacion());
	            data.put("clima", climaMap);
	        }
	        // Zonas turísticas
	        List<ZonaTuristica> zonas = zonaRepository.findByEstacion_EstId(est.getEstId());
	        List<Map<String, Object>> zonaLista = new ArrayList<>();
	        for (ZonaTuristica z : zonas) {
	            Map<String, Object> zm = new HashMap<>();
	            zm.put("nombre", z.getZotNombre());
	            zm.put("direccion", z.getZotDireccion());
	            zm.put("descripcion", z.getZotDescripcion());
	            zm.put("dificultad", z.getZotDificultad());
	            zm.put("distancia", z.getZotDistanciaMetros());
	            zonaLista.add(zm);
	        }
	        data.put("zonas", zonaLista);

	        resultado.add(data);
	    }
	    return resultado;
	}
}