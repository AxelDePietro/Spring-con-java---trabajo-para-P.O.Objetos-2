package com.oo2.grupo4.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.dto.ActualizacionResponseDTO;
import com.oo2.grupo4.security.UserDetailsImpl;
import com.oo2.grupo4.services.implementation.ActualizacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actualizacion")
@Tag(name = "Administración de Actualizaciones de Ticket", description = "Endpoints para gestión de actualizaciones de un ticket")

public class ActualizacionRestController {

	private final ActualizacionService actualizacionService;

	@Operation(summary = "crear una actulizacion de un ticket (idTicket)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Actualizaciones creada correctamente") })
	@PostMapping("/crearActualizacionRest/{idTicket}")
	public ResponseEntity<ActualizacionResponseDTO> crearActualizacion(@PathVariable int idTicket,
			@RequestBody ActualizacionCreateDTO dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

		int idPersona = userDetails.getLogin().getPersona().getIdPersona();

		ActualizacionCreateDTO dtoNuevo = new ActualizacionCreateDTO(dto.contenido(), idPersona, idTicket);

		ActualizacionResponseDTO creada = actualizacionService.crearActualizacion(dtoNuevo);

		return ResponseEntity.status(HttpStatus.CREATED).body(creada);
	}

	@Operation(summary = "trae la lista de actuliaciones de un ticket (idTicket)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Actualizaciones obtenida correctamente") })
	@GetMapping("/listaActualizacionesRest/{idTicket}")
	public ResponseEntity<List<ActualizacionResponseDTO>> listaActualizaciones(@PathVariable int idTicket) {
		List<ActualizacionResponseDTO> actualizaciones = actualizacionService
				.listaActualizacionesDTOporTicket(idTicket);

		return ResponseEntity.ok(actualizaciones);
	}

	@Operation(summary = "elimina  una actulizacion de un ticket (idTicket)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Actualización eliminada correctamente") })
	@DeleteMapping("/eliminarActualizacionRest/{idTicket}/{idActualizacion}")
	public ResponseEntity<Void> eliminarActualizacion(@PathVariable int idTicket, @PathVariable int idActualizacion) {

		actualizacionService.delete(idTicket, idActualizacion);

		return ResponseEntity.noContent().build();
	}
}
