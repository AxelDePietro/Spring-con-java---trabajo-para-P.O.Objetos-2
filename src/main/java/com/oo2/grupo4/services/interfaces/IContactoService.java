package com.oo2.grupo4.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Contacto;
import com.oo2.grupo4.entities.Persona;

public interface IContactoService {

	Contacto crearContacto(String telefono, String email, Persona persona);

	Contacto traerPorId(int idContacto);

	int actualizarContacto(int idContacto, String telefono, String email);

	boolean existeEmail(String email);

	void eliminarContacto(int idContacto);

	public void save(Contacto contacto);

}
