package com.oo2.grupo4.services.interfaces;

import java.util.List;

import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Ticket;

public interface IPersonaService {

	Persona traerPorId(int idPersona);

	Persona traerPorDni(Long dni);

	int actualizarPersona(int idPersona, String nombre, String apellido, Long dni);

	void eliminarPersona(int idPersona);

	void validarDniNoExiste(Long dni);

	public boolean existsByDni(Long dni);
	
	public List<Persona> getAll();

}
