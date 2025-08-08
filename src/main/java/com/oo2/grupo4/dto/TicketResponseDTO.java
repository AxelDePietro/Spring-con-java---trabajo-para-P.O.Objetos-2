package com.oo2.grupo4.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketResponseDTO (
	
		@NotNull
	 	@Schema(description = "ID del ticket", example = "1")
		Integer idTicket,
		
		@NotNull
		@Schema(description = "Titulo del ticket", example = "Solucion")
		 String titulo,
		 
		 @NotNull
		 @Schema(description = "Descripcion del ticket", example = "Problema")
		 String descripcion,
		 
		 LocalDateTime fechaCreacion,
		 LocalDateTime fechaCierre,
	 
	 	@NotBlank 
	 	@Schema(description = "Nombre de la persona", example = "Juan")
		String nombrePersona,
	 
		@NotBlank 
		@Schema(description = "Apellido de la persona", example = "Perez")
		String apellidoPersona,
	 
		@NotBlank 
	    @Schema(description = "Estado del ticket", example = "Cerrado")
		String estado,
		
		@NotBlank 
	    @Schema(description = "Prioridad del ticket", example = "Alta")
		String prioridad,
		
		@NotBlank 
	    @Schema(description = "Tipo del ticket", example = "Sugerencia")
		String tipoDeTicket,
		
		@NotBlank 
	    @Schema(description = "Nombre del empleado", example = "Lucas")
		String nombreEmpleado, 
		
		@NotBlank 
	    @Schema(description = "Apellido del empleado", example = "Lopez")
		String apellidoEmpleado
) {}
