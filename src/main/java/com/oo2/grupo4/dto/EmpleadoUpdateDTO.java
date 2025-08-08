package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpleadoUpdateDTO (
		
		@Schema(description = "ID de la persona a modificar", example = "1")
		int idPersona,
		
		@NotBlank
		@Schema(description = "Nombre del empleado", example = "Juan")
		String nombre,      // heredado de Persona
		
	    @NotBlank 
	    @Schema(description = "Apellido del empleado", example = "Pérez")
		String apellido,    // heredado de Persona    
		
	    @NotNull @Min(1) 
		@Schema(description = "DNI del empleado", example = "12345678")
		Long dni,     		// heredado de Persona
		
	    @NotNull 
	    @Schema(description = "Número de legajo", example = "1234")
		int legajo,
		
	    @NotNull 
	    @Schema(description = "ID del área al que pertenece", example = "5")
		int idArea,
		
	    @NotBlank 
	    @Schema(description = "Rol del empleado", example = "Trainee")
		String rol
		
){}

