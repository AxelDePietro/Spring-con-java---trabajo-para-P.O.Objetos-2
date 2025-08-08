package com.oo2.grupo4.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByClienteIdPersona(int idCliente);

	List<Ticket> findByPrioridadTipo(String tipo);

	List<Ticket> findByEstadoTipo(String tipo);

	List<Ticket> findByTipoDeTicketTipo(String tipo);

	List<Ticket> findByTitulo(String titulo);

	boolean existsByTitulo(String titulo);

}
