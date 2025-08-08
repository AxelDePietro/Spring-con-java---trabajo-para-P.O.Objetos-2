package com.oo2.grupo4.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Prioridad;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

	Empleado findByLegajo(Integer legajo); // Buscar por legajo
	
	List<Empleado> findByAreaIdArea(int idArea); // Buscar empleados por id del área

	List <Empleado> findByRol (String rol); //Lista de empleados por rol
	
	List<Empleado> findByRolNot(String rol);
}
