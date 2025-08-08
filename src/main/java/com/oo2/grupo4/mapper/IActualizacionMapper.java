package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.dto.ActualizacionResponseDTO;
import com.oo2.grupo4.entities.Actualizacion;

@Mapper(componentModel = "spring")
public  interface IActualizacionMapper {
	
	@Mapping(target = "nombreEmpleado", source = "empleado.nombre")
    @Mapping(target = "apellidoEmpleado", source = "empleado.apellido")	
	@Mapping(target = "idTicket", source = "ticket.idTicket")
	ActualizacionResponseDTO toDTO ( Actualizacion actualizacion);
	
	@Mapping(target = "ticket.idTicket", source = "idTicket")
	@Mapping(target = "empleado", ignore = true)
	@Mapping(target = "fechaActualizacion", ignore = true)
	@Mapping(target = "idActualizacion", ignore = true)
	Actualizacion toEntity (ActualizacionCreateDTO actualizacionCreateDTO);
	
}
