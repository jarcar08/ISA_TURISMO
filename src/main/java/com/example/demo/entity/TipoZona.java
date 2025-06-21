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
@Table(name = "tipo_zona")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoZona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tzId")
	private int tzId;

	@Column(name = "tzNombre")
	private String tzNombre;

}
