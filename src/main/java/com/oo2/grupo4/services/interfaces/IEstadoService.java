package com.oo2.grupo4.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Estado;

public interface IEstadoService {

	Estado findById(Integer id);

	Estado findByTipo(String tipo);

	List<Estado> getAll();

}
