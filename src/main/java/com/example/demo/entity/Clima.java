package com.example.demo.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
@Table(name = "clima")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Clima {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliId")
	private int cliId;

	@Column(name = "cliEstado")
	private String cliEstado;

	@Column(name = "cliTempMax")
	private Double cliTempMax;

	@Column(name = "cliTempMin")
	private Double cliTempMin;

	@Column(name = "cliRecomendacion")
	private String cliRecomendacion;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "cliFecha")
	private LocalDate clifecha;

	@ManyToOne
	@JoinColumn(name = "cliestId", referencedColumnName = "estId")
	private Estacion estacion;
}