package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "tipo_de_ticket")
public class TipoDeTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoDeTicket;

	private String tipo;

	@OneToMany(mappedBy = "tipoDeTicket", fetch = FetchType.LAZY)
	private List<Ticket> tickets;

}
