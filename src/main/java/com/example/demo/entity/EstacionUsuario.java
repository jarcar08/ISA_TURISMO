package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "estacion_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "euId")
	private int euId;

	@ManyToOne
	@JoinColumn(name = "euestId", referencedColumnName = "estId")
	private Estacion estacion;

	@ManyToOne
	@JoinColumn(name = "euusuId", referencedColumnName = "usuId")
	private Usuario usuario;

	@Column(name = "euFechaSeleccion", insertable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime euFechaSeleccion;

}
