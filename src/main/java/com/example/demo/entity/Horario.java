package com.example.demo.entity;

import java.time.LocalTime;
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
@Table(name = "horario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horId")
	private int horId;

	@ManyToOne
	@JoinColumn(name = "horestId", referencedColumnName = "estId")
	private Estacion estacion;

	@JsonFormat(pattern = "HH:mm")
	@Column(name = "horLlegada")
	private LocalTime horLlegada;

	@JsonFormat(pattern = "HH:mm")
	@Column(name = "horSalida")
	private LocalTime horSalida;

}
