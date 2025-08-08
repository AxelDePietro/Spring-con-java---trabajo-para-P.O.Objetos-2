package com.oo2.grupo4.repositories;

import com.oo2.grupo4.entities.Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Integer> {

	Optional<Estado> findById(Integer id);

	Optional<Estado> findByTipo(String tipo);

}
