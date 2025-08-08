package com.oo2.grupo4.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.dto.ClienteCreateDTO;
import com.oo2.grupo4.dto.ClienteResponseDTO;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.mapper.IClienteMapper;
import com.oo2.grupo4.mapper.ITicketMapper;
import com.oo2.grupo4.repositories.IClienteRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

	private final IClienteRepository clienteRepository;
	private final PersonaService personaService;
	private final ITicketRepository ticketRepository;
	private final ITicketMapper ticketMapper;

	private final IClienteMapper clienteMapper;

	
	//METODOS MAV
	
	@Override
	public Cliente crearCliente(String nombre, String apellido, Long dni, String nroCliente) {
		personaService.validarDniNoExiste(dni);

		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni);
		cliente.setNroCliente(nroCliente);

		return clienteRepository.save(cliente);
	}

	@Override
	public void completarCliente(int idPersona, String nroCliente) {
		Cliente cliente = clienteRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + idPersona));

		cliente.setNroCliente(nroCliente);
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente traerPorId(int idPersona) {
		return clienteRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + idPersona));
	}
	@Override
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}
	
	@Override
	public List<TicketResponseDTO> getAllByClienteId(int idCliente){
		return ticketRepository.findAll().stream().filter(t -> t.getCliente() != null && t.getCliente().getIdPersona() ==idCliente)
				.map(ticketMapper::toDTO).collect(Collectors.toList());
	}
	
	//METODOS PARA REST 
	
	public List<ClienteResponseDTO> getAllDTOs() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO getDTOById(int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

	
	public Cliente crearClienteDTO(ClienteCreateDTO dto) {
        personaService.validarDniNoExiste(dto.dni());
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteRepository.save(cliente);
    }
	
	
	
	
	

}