package com.oo2.grupo4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginCreateDTO (
		
		@NotBlank @Email 
		@Schema(description = "Email", example = "admin@gmail.com")
		String correo,
		
		@NotBlank 
		@Schema(description = "Password", example = "admin123")
		String contrasenia
		
){}
