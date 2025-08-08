package com.oo2.grupo4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oo2.grupo4.entities.Contacto;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Integer> {

	boolean existsByEmail(String email);
}
