package com.example.demo.entity;

import java.time.LocalDateTime;
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
@Table(name = "posicion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Posicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "posId")
	private int posId;

	@ManyToOne
	@JoinColumn(name = "postrenId", referencedColumnName = "trenId")
	private Tren tren;

	@ManyToOne
	@JoinColumn(name = "posestId", referencedColumnName = "estId")
	private Estacion estacion;

	@Column(name = "posLat")
	private Double posLat;

	@Column(name = "posLong")
	private Double posLong;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "posFechaHora")
	private LocalDateTime posFechaHora;

}
