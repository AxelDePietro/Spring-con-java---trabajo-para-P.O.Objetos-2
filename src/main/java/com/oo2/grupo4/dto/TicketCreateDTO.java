package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TicketCreateDTO(
		
		@NotNull
		@Schema(description = "Titulo del ticket", example = "Solucion")
		 String titulo,
		 
		 @NotNull
		 @Schema(description = "Descripcion del ticket", example = "Problema")
		 String descripcion,
		 
		 @NotNull
		 @Schema(description = "ID del tipo de ticket", example = "1")
		 int idTipoDeTicket,
		 
		 @NotNull
		 @Schema(description = "ID del cliente", example = "1")
		 Integer idCliente
		) {}
