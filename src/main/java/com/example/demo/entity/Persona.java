package com.example.demo.entity;

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
@Table(name = "persona")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perId")
	private int perId;

	@Column(name = "perDni", unique = true)
	private String perDni;

	@Column(name = "perApellidoPaterno")
	private String perApellidoPaterno;

	@Column(name = "perApellidoMaterno")
	private String perApellidoMaterno;

	@Column(name = "perNombres")
	private String perNombres;

	@Column(name = "perCorreo")
	private String perCorreo;
	
	@OneToOne
	@JoinColumn(name = "pertpId", referencedColumnName = "tpId")
	private TipoPasajero tipopasajero;

}
