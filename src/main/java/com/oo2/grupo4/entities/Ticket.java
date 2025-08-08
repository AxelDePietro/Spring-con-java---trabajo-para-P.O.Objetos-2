package com.oo2.grupo4.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTicket;
	private String titulo;
	private String descripcion;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaCierre;

	@ManyToOne
	@JoinColumn(name = "tipo_ticket_id")
	private TipoDeTicket tipoDeTicket;

	@ManyToOne
	@JoinColumn(name = "prioridad_id")
	private Prioridad prioridad;

	@ManyToOne
	@JoinColumn(name = "cliente_id_persona")
	private Cliente cliente;

	@ManyToOne 
	@JoinColumn(name = "empleado_id_persona")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	private List<Actualizacion> actualizaciones;

}
