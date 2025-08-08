package com.oo2.grupo4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo4.entities.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByNroCliente(String nroCliente); // Buscar por número de cliente

}
