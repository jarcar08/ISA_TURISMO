package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_pasajero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPasajero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tpId")
	private int tpId;

	@Column(name = "tpNombre")
	private String tpNombre;

	@Column(name = "tpDescripcion")
	private String tpDescripcion;

}
