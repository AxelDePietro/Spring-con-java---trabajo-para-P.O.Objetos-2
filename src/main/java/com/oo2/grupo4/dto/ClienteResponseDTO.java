package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteResponseDTO (
		
		@Schema(description = "ID de la persona", example = "1")
		int idPersona, //heredado de persona
		
		
		@NotBlank
		@Schema(description = "Apellido del cliente", example = "Sanchez")
		String apellido,  // heredado de Persona
		
		@NotNull
		@Min(1)
		@Schema(description = "Dni del cliente", example = "15635487")
		Long dni,// heredado de Persona
		
		@NotNull
		@Schema(description = "Nro del cliente", example = "4585")
		int nroCliente
) {}
