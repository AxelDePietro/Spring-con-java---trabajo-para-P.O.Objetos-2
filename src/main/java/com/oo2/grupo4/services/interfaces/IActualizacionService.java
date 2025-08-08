package com.oo2.grupo4.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Empleado;

public interface IActualizacionService {

	void delete(int idTicket, int idActualizacion);

	// en interface
	
	List<Actualizacion> getAllByTicketId(int idTicket);

	Optional<Actualizacion> findByIdActualizacion(int idActualizacion);

	List<Actualizacion> findByTicketIdTicket(int idTicket);

	List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado);

	List<Actualizacion> findByFechaActualizacion(LocalDateTime fechaActualizacion);

}
