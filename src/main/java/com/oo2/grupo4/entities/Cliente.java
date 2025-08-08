package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@PrimaryKeyJoinColumn(name = "persona_id_persona") // Hereda la PK de Persona
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente extends Persona {

	private String nroCliente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Ticket> tickets;

}
