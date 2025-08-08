package com.oo2.grupo4.services.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.dto.ActualizacionResponseDTO;
import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.exceptions.TicketSinActualizaciones;
import com.oo2.grupo4.mapper.IActualizacionMapper;
import com.oo2.grupo4.repositories.IActualizacionRepository;
import com.oo2.grupo4.repositories.IEmpleadoRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.IActualizacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActualizacionService implements IActualizacionService {

	private final IActualizacionRepository actualizacionRepository;

	private final IActualizacionMapper mapper;

	private final IEmpleadoRepository empleadoRepository;

	private final ITicketRepository ticketRepository;

	// mapper / dto

	public ActualizacionResponseDTO crearActualizacion(ActualizacionCreateDTO dto) {
		Actualizacion actualizacion = mapper.toEntity(dto);

		Empleado empleado = empleadoRepository.findById(dto.idEmpleado()).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		Ticket ticket = ticketRepository.findById(dto.idTicket()).orElseThrow(() -> new RuntimeException("ticket no encontrado"));
		
		actualizacion.setEmpleado(empleado);
		actualizacion.setTicket(ticket);
		actualizacion.setFechaActualizacion(LocalDateTime.now());

		actualizacionRepository.save(actualizacion);
		
		return mapper.toDTO(actualizacion);
	}

	public List<ActualizacionResponseDTO> listaActualizacionesDTOporTicket(int idTicket) {
		List<ActualizacionResponseDTO> actualizacionResponseDTOs = new ArrayList<>();

		for (Actualizacion a : actualizacionRepository.findAll()) {
			if (a.getTicket().getIdTicket() == idTicket) {
				actualizacionResponseDTOs.add(mapper.toDTO(a));
			}
		}

		if (actualizacionResponseDTOs.isEmpty()) {
			throw new TicketSinActualizaciones(idTicket);
		}

		return actualizacionResponseDTOs;
	}

	// repository

	

	@Override
	public Optional<Actualizacion> findByIdActualizacion(int idActualizacion) {
		return actualizacionRepository.findByIdActualizacion(idActualizacion);
	}

	@Override
	public List<Actualizacion> findByTicketIdTicket(int idTicket) {
		return actualizacionRepository.findByTicketIdTicket(idTicket);
	}

	@Override
	public List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado) {
		return actualizacionRepository.findByEmpleadoIdPersona(idEmpleado);
	}

	@Override
	public List<Actualizacion> findByFechaActualizacion(LocalDateTime fechaActualizacion) {
		return actualizacionRepository.findByFechaActualizacion(fechaActualizacion);

	}

	@Override
	public List<Actualizacion> getAllByTicketId(int idTicket) {
		List<Actualizacion> actualizaciones = new ArrayList<>();

		for (Actualizacion a : actualizacionRepository.findAll()) {
			if (a.getTicket().getIdTicket() == idTicket) {
				actualizaciones.add(a);
			}
		}

		if (actualizaciones.isEmpty()) {
			throw new TicketSinActualizaciones(idTicket);
		}

		return actualizaciones;
	}

	@Override
	public void delete(int idTicket, int idActualizacion) {
		Actualizacion actualizacion = null;
		for (Actualizacion a : actualizacionRepository.findAll()) {
			if (a.getTicket().getIdTicket() == idTicket && a.getIdActualizacion() == idActualizacion) {
				actualizacion = a;
			}
		}

		actualizacionRepository.delete(actualizacion);
	}

}
