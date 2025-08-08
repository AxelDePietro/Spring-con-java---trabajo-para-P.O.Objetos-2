package com.oo2.grupo4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, Integer> {

	boolean existsByCorreo(String correo);

	Optional<Login> findByCorreo(String correo);

}
