package com.oo2.grupo4.services.interfaces;


import java.util.List;

import com.oo2.grupo4.dto.ClienteCreateDTO;
import com.oo2.grupo4.dto.ClienteResponseDTO;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.entities.Cliente;

public interface IClienteService {

	Cliente crearCliente(String nombre, String apellido, Long dni, String nroCliente);

	void completarCliente(int idPersona, String nroCliente);

	Cliente traerPorId(int idPersona);
	
	public List<Cliente> getAll();
	

	public List<TicketResponseDTO> getAllByClienteId(int idCliente);

	//List<Ticket> getAllByClienteId(int idCliente);
	
	//METODOS DTO
	
	 public Cliente crearClienteDTO(ClienteCreateDTO dto);
	 
	 public List<ClienteResponseDTO> getAllDTOs();
	 
	 public ClienteResponseDTO getDTOById(int id);

}
