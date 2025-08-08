package com.oo2.grupo4.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.repositories.IEstadoRepository;
import com.oo2.grupo4.services.interfaces.IEstadoService;

@Service
public class EstadoService implements IEstadoService {

	private final IEstadoRepository estadoRepository;

	public EstadoService(IEstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	@Override
	public Estado findById(Integer id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Estado no encontrada con ID: " + id));
	}

	@Override
	public Estado findByTipo(String tipo) {
		return estadoRepository.findByTipo(tipo)
				.orElseThrow(() -> new IllegalArgumentException("Estado no encontrada con el tipo: " + tipo));
	}

	@Override
	public List<Estado> getAll() {
		return estadoRepository.findAll();
	}

}
