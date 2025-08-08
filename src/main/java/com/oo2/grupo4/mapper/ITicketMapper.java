package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oo2.grupo4.dto.TicketCreateDTO;
import com.oo2.grupo4.dto.TicketModificarDTO;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.entities.Ticket;

@Mapper(componentModel = "spring")
public interface ITicketMapper {

	// Ticket → TicketResponseDTO
	@Mapping(target = "nombrePersona", source = "cliente.nombre")
	@Mapping(target = "apellidoPersona", source = "cliente.apellido")
	@Mapping(target = "estado", source = "estado.tipo")
	@Mapping(target = "prioridad", source = "prioridad.tipo")
	@Mapping(target = "tipoDeTicket", source = "tipoDeTicket.tipo")
	@Mapping(target = "nombreEmpleado", expression = "java(ticket.getEmpleado() != null ? ticket.getEmpleado().getNombre() : null)")
	@Mapping(target = "apellidoEmpleado", expression = "java(ticket.getEmpleado() != null ? ticket.getEmpleado().getApellido() : null)")
	@Mapping(target = "fechaCreacion", source = "fechaCreacion")
	@Mapping(target = "fechaCierre", source = "fechaCierre")

	TicketResponseDTO toDTO(Ticket ticket);

	// TicketCreateDTO → Ticket
	@Mapping(target = "tipoDeTicket.idTipoDeTicket", source = "idTipoDeTicket")
	@Mapping(target = "cliente", ignore = true)
	@Mapping(target = "empleado", ignore = true)
	@Mapping(target = "estado", ignore = true)
	@Mapping(target = "prioridad", ignore = true)
	@Mapping(target = "fechaCreacion", ignore = true)
	@Mapping(target = "fechaCierre", ignore = true)
	@Mapping(target = "idTicket", ignore = true)
	Ticket toEntity(TicketCreateDTO dto);

	// TicketModificarDTO → Ticket
	@Mapping(target = "tipoDeTicket.idTipoDeTicket", source = "idTipoDeTicket")
	@Mapping(target = "estado", ignore = true)
	@Mapping(target = "prioridad", ignore = true)
	@Mapping(target = "cliente", ignore = true)
	@Mapping(target = "empleado", ignore = true)
	@Mapping(target = "fechaCreacion", ignore = true)
	@Mapping(target = "fechaCierre", ignore = true)
	Ticket toEntity(TicketModificarDTO dto);
}
