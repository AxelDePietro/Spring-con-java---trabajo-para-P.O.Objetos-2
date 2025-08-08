package com.oo2.grupo4.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLogin;
	private String correo;
	private String contrasenia;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_id_persona")
	private Persona persona;

}
