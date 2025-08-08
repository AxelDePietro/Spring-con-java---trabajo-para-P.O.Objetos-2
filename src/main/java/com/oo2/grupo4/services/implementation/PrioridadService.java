package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.entities.Prioridad;
import com.oo2.grupo4.repositories.IPrioridadRepository;
import com.oo2.grupo4.services.interfaces.IPrioridadService;

@Service
public class PrioridadService implements IPrioridadService {

	private final IPrioridadRepository prioridadRepository;

	public PrioridadService(IPrioridadRepository prioridadRepository) {
		this.prioridadRepository = prioridadRepository;
	}

	@Override
	public Prioridad findById(Integer id) {
		return prioridadRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Prioridad no encontrada con ID: " + id));
	}

	@Override
	public Prioridad findByTipo(String tipo) {
		return prioridadRepository.findByTipo(tipo)
				.orElseThrow(() -> new IllegalArgumentException("Prioridad no encontrada con el tipo: " + tipo));
	}

	@Override
	public List<Prioridad> getAll() {
		return prioridadRepository.findAll();
	}

}
