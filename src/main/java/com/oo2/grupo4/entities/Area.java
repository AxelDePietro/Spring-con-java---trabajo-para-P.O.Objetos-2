package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArea;

	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private List<Empleado> empleados;

}
