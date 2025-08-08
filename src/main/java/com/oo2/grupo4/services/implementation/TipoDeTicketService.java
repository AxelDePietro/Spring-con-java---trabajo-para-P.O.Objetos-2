package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.entities.TipoDeTicket;
import com.oo2.grupo4.repositories.ITipoDeTicketRepository;
import com.oo2.grupo4.services.interfaces.ITipoDeTicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TipoDeTicketService implements ITipoDeTicketService {

	private final ITipoDeTicketRepository tipoDeTicketRepository;

	@Override
	public TipoDeTicket findById(int id) {
		return tipoDeTicketRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("TipoDeTicket no encontrada con ID: " + id));
	}

	@Override
	public TipoDeTicket findByTipo(String tipo) {
		return tipoDeTicketRepository.findByTipo(tipo)
				.orElseThrow(() -> new IllegalArgumentException("TipoDeTicket no encontrada con ID: " + tipo));
	}

	@Override
	public List<TipoDeTicket> getAll() {
		return tipoDeTicketRepository.findAll();
	}

}
