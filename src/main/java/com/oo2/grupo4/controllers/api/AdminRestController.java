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

import com.oo2.grupo4.dto.EmpleadoCreateDTO;
import com.oo2.grupo4.dto.EmpleadoUpdateDTO;
import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.mapper.IEmpleadoMapper;
import com.oo2.grupo4.services.implementation.EmpleadoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/empleados")
@Tag(name = "Acciones de Administrador", description = "Endpoints para gestión de empleados")
public class AdminRestController {

    private final EmpleadoService empleadoService;
    private final IEmpleadoMapper empleadoMapper;

    @Operation(summary = "Obtener todos los empleados")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    @GetMapping("/listaPersonas")
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoService.getAllDTOs());
    }

    @Operation(summary = "Crear un nuevo empleado")
    @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente")
    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(
        @Valid @RequestBody EmpleadoCreateDTO dto) {

        EmpleadoDTO creado = empleadoMapper.toDTO(empleadoService.crearEmpleado(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    @Operation(summary = "Actualizar un empleado existente")
    @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente")
    @PutMapping("/modificar/{idEmpleado}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(
            @PathVariable int idEmpleado,
            @Valid @RequestBody EmpleadoUpdateDTO dto) {

        if (dto.idPersona() != idEmpleado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EmpleadoDTO actualizado = empleadoMapper.toDTO(empleadoService.actualizarEmpleado(dto));
        return ResponseEntity.ok(actualizado);
    }

}
