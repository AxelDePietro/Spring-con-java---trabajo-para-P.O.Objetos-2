package com.oo2.grupo4.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo4.dto.TicketCreateDTO;
import com.oo2.grupo4.dto.TicketModificarDTO;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.mapper.ITicketMapper;
import com.oo2.grupo4.services.implementation.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
@Tag(name = "Administración de Ticket", description = "Endpoints para gestión de Tickets")

public class TicketRestController {
	private final TicketService ticketService;
	private final ITicketMapper ticketMapper;

	@Operation(summary = "Obtener todos los tickets")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de tickets obtenida correctamente")})
	@GetMapping("/listaTickets")
	public ResponseEntity<List<TicketResponseDTO>> getAllTicketResponseDTOs() {
		return ResponseEntity.ok(ticketService.mostrarTickets());
	}

	@Operation(summary = "Crear un nuevo ticket")
	@ApiResponse(responseCode = "201", description = "Ticket creado exitosamente")
	@PostMapping("/crearTicket")
	public ResponseEntity<TicketResponseDTO> crearTicket(@Valid @RequestBody TicketCreateDTO dto) {
		TicketResponseDTO creado = ticketMapper.toDTO(ticketService.crearTicket(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	@Operation(summary = "Modificar un ticket existente")
	@ApiResponse(responseCode = "200", description = "Ticket modificado exitosamente")
	@PutMapping("/modificar/{idTicket}")
	public ResponseEntity<TicketResponseDTO> modificarTicket(@PathVariable int idTicket,
			@RequestBody TicketModificarDTO dto) {
		if (dto.idTicket() != idTicket) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		TicketResponseDTO actualizado = ticketMapper.toDTO(ticketService.modificarTicket(dto));
		return ResponseEntity.ok(actualizado);
	}

}
