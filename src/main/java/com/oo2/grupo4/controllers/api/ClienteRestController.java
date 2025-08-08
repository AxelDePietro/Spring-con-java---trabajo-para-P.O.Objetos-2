package com.oo2.grupo4.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo4.dto.ClienteCreateDTO;
import com.oo2.grupo4.dto.ClienteResponseDTO;
import com.oo2.grupo4.mapper.IClienteMapper;
import com.oo2.grupo4.services.implementation.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
@Tag(name = "Administración de Clientes", description = "Endpoints para gestión de clientes")
public class ClienteRestController {
	
	private final ClienteService clienteService;
	private final IClienteMapper clienteMapper;
	
	@Operation(summary = "Obtener todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
    @GetMapping("/listaClientes")
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllDTOs());
    }

    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @PostMapping("/crearCliente")
    public ResponseEntity<ClienteResponseDTO> crearCliente(
        @RequestBody(
            description = "Datos para crear un cliente",
            required = true,
            content = @Content(schema = @Schema(implementation = ClienteCreateDTO.class))
        )
        @Valid @org.springframework.web.bind.annotation.RequestBody ClienteCreateDTO dto) {

        ClienteResponseDTO creado = clienteMapper.toDTO(clienteService.crearClienteDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

}
