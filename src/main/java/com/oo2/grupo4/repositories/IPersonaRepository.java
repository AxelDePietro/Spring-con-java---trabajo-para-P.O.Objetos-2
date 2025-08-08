package com.oo2.grupo4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo4.entities.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

	boolean existsByDni(Long dni);

	Optional<Persona> findByDni(Long dni);

}
