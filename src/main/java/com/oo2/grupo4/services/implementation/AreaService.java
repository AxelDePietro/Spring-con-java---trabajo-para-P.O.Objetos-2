package com.oo2.grupo4.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Area;
import com.oo2.grupo4.repositories.IAreaRepository;
import com.oo2.grupo4.services.interfaces.IAreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService implements IAreaService {

	private final IAreaRepository areaRepository;

	@Override
	public List<Area> traerTodas() {
		return areaRepository.findAll();
	}

	@Override
	public Area traerPorId(int idArea) {
		return areaRepository.findById(idArea)
				.orElseThrow(() -> new IllegalArgumentException("Area no encontrada con ID: " + idArea));
	}
	
	@Override
	public Area traerPorNombre (String nombre) {
		return areaRepository.findByNombre(nombre)
				.orElseThrow(() -> new IllegalArgumentException("Area no encontrada con nombre: " + nombre));
	}
}