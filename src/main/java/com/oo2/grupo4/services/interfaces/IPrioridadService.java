package com.oo2.grupo4.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.entities.Prioridad;

public interface IPrioridadService {

	Prioridad findById(Integer id);

	Prioridad findByTipo(String tipo);

	List<Prioridad> getAll();

}
