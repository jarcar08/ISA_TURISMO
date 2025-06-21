package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tren")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tren {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trenId")
	private int trenId;

	@Column(name = "trenNombre")
	private String trenNombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "trenEstado")
	private Estado trenEstado;

}
