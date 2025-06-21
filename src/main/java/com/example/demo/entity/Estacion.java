package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "estacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estId")
	private int estId;
	
	@Column(name = "estNumero")
	private int estNumero;
	
	@Column(name = "estNombre")
	private String estNombre;
	
	@Column(name = "estUbicacion")
	private String estUbicacion;
	
	@Column(name = "estEstado")
	private int estEstado;
	
	@Column(name = "estLat")
	private double estLat;
	
	@Column(name = "estLong")
	private double estLong;
	
	@OneToMany(mappedBy = "estacion")
    private List<ZonaTuristica> zonas;
	
	
}
