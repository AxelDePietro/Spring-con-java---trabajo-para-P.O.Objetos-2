package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oo2.grupo4.dto.EmpleadoCreateDTO;
import com.oo2.grupo4.dto.EmpleadoUpdateDTO;
import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.entities.Empleado;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapper {

    // Para REST: de CreateDTO a entidad Empleado
    Empleado toEntity(EmpleadoCreateDTO dto);

    // Para REST: de UpdateDTO a entidad Empleado
    Empleado toEntity(EmpleadoUpdateDTO dto);

    // Para REST: de entidad Empleado a DTO de salida
    @Mapping(target = "idPersona", source = "idPersona")
    @Mapping(target = "idArea", source = "area.idArea")
    @Mapping(target = "area", source = "area.nombre")
    EmpleadoDTO toDTO(Empleado empleado);
}
