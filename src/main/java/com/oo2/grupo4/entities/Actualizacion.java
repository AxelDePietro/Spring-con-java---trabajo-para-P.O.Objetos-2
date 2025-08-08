package com.oo2.grupo4.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Actualizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idActualizacion;

	private String contenido;
	private LocalDateTime fechaActualizacion;

	@ManyToOne
	@JoinColumn(name = "empleado_id_persona")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

}
