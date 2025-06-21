package com.example.demo.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuId")
	private int usuId;

	@Column(name = "usuNombre")
	private String usuNombre;

	@Column(name = "usuContrasenia")
	private String usuContrasenia;

	@OneToOne
	@JoinColumn(name = "usuPerId", referencedColumnName = "perId")
	private Persona persona;

	@OneToOne
	@JoinColumn(name = "usuRolId", referencedColumnName = "rolId")
	private Rol rol;

	@Column(name = "usuEstado")
	private int usuEstado;

	@Column(name = "usuFechaRegistro", insertable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime usuFechaRegistro;
}
