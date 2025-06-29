package com.example.demo.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preferencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "preId")
	private int preId;

	@OneToOne
	@JoinColumn(name = "preusuId", referencedColumnName = "usuId")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "preestId", referencedColumnName = "estId")
	private Estacion estacion;

	@Column(name = "preTipoZona")
	private String preTipoZona;

	@Enumerated(EnumType.STRING)
	@Column(name = "preDificultad")
	private Dificultad preDificultad;

	@Column(name = "preDistanciaMax")
	private double preDistanciaMax;

	@Column(name = "preTiemppoMax")
	private int preTiempoMax;

	@Column(name = "preFecha", insertable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime preFecha;

}
