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
@Table(name = "tarifa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taId")
	private int taId;

	@Column(name = "taPrecioPasaje")
	private Double taPrecioPasaje;

	@OneToOne
	@JoinColumn(name = "tatpId", referencedColumnName = "tpId")
	private TipoPasajero tipopasajero;

}
