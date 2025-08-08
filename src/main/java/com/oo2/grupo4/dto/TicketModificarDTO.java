	package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TicketModificarDTO (
		
			@NotNull
		 	@Schema(description = "ID del ticket", example = "1")
			Integer idTicket,
			
			@NotNull
			@Schema(description = "Titulo del ticket", example = "Solucion")
			 String titulo,
			 
			 @NotNull
			 @Schema(description = "Descripcion del ticket", example = "Problema")
			 String descripcion,
			 
			 @NotNull
			 @Schema(description = "ID del tipo ticket", example = "1")
			 int idTipoDeTicket,
			 
			 @NotNull
			 @Schema(description = "ID de la prioridad del ticket", example = "1")
			Integer idPrioridad,
			
			@NotNull
			 @Schema(description = "ID del estado del ticket", example = "1")
			Integer idEstado
			){}
