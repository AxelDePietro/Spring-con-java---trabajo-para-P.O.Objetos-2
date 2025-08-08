package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmpleadoDTO (
		
	    @Schema(description = "ID de la persona", example = "1")
	    int idPersona,

	    @Schema(description = "Nombre del empleado", example = "Juan")
	    String nombre,

	    @Schema(description = "Apellido del empleado", example = "Pérez")
	    String apellido,

	    @Schema(description = "DNI del empleado", example = "12345678")
	    Long dni,

	    @Schema(description = "Número de legajo", example = "1234")
	    int legajo,

	    @Schema(description = "ID del área", example = "5")
	    int idArea,

	    @Schema(description = "Nombre del área", example = "Administración")
	    String area,

	    @Schema(description = "Rol del empleado", example = "Trainee")
	    String rol
    ){}
