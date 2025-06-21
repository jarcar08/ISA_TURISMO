package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zona_turistica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaTuristica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zotId")
	private int zotId;

	@Column(name = "zotNombre")
	private String zotNombre;

	@Column(name = "zotDescripcion")
	private String zotDescripcion;

	@Column(name = "zotDireccion")
	private String zotDireccion;

	@Column(name = "zotDistanciaMetros")
	private Double zotDistanciaMetros;

	@Column(name = "zotTiempoCaminata")
	private int zotTiempoCaminata;

	@Enumerated(EnumType.STRING)
	@Column(name = "zotDificultad")
	private Dificultad zotDificultad;
	
	@ManyToOne
    @JoinColumn(name = "zotestId", referencedColumnName = "estId")
    private Estacion estacion;
	
	@ManyToOne
    @JoinColumn(name = "zottzId", referencedColumnName = "tzId")
    private TipoZona tipozona;

}
