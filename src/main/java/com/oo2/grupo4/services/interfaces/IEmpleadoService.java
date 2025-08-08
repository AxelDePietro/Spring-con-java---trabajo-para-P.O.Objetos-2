package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.dto.EmpleadoCreateDTO;
import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.dto.EmpleadoUpdateDTO;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Ticket;


import java.util.List;

public interface IEmpleadoService {
	
	Empleado crearEmpleado(String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol);

	public int modificarEmpleado(int idPersona, String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol);

	List<Empleado> obtenerEmpleadosPorArea(int idArea);
	
	List <Empleado> traerNoAdmi (String rol);

	Empleado traerPorId(int idPersona);
	
	public List<TicketResponseDTO> getAllByEmpleadoId(int idEmpleado);
	
	public void delete(int idPersona);
	
	List<Empleado> getAll();
	
	//Metodos DTO
	 public Empleado crearEmpleado(EmpleadoCreateDTO dto);
	 
	 public Empleado actualizarEmpleado(EmpleadoUpdateDTO dto);
	 
	 public List<EmpleadoDTO> getAllDTOs();
	 
	 public EmpleadoDTO getDTOById(int id);
}