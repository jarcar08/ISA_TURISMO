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
@Table(name = "posicion_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PosicionUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "puId")
	private int puId;

	@ManyToOne
	@JoinColumn(name = "puusuId", referencedColumnName = "usuId")
	private Usuario usuario;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "puFechaHora")
	private LocalDateTime puFechaHora;

	@Column(name = "puLat")
	private Double puLat;

	@Column(name = "puLong")
	private Double puLong;

}
