package com.oo2.grupo4.repositories;

import com.oo2.grupo4.entities.TipoDeTicket;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDeTicketRepository extends JpaRepository<TipoDeTicket, Integer> {

	Optional<TipoDeTicket> findByTipo(String tipo);

}
