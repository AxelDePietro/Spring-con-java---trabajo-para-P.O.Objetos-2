package com.oo2.grupo4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Actualizacion;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface IActualizacionRepository extends JpaRepository<Actualizacion, Integer> {

	Optional<Actualizacion> findByIdActualizacion(int idActualizacion);

	List<Actualizacion> findByTicketIdTicket(int idTicket);

	List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado);

	List<Actualizacion> findByFechaActualizacion(LocalDateTime fechaActualizacion);

}
