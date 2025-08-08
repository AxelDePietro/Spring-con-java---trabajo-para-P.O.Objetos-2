package com.oo2.grupo4.dto;

import java.time.LocalDateTime;

public record ActualizacionResponseDTO (
		
		Integer idActualizacion,
		String contenido,
		LocalDateTime fechaActualizacion,
		String nombreEmpleado,
		String apellidoEmpleado,
		Integer idTicket)
{}
