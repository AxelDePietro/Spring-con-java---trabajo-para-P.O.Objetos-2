package com.oo2.grupo4.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.oo2.grupo4.dto.EmpleadoCreateDTO;
import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.dto.EmpleadoUpdateDTO;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.entities.Area;
import com.oo2.grupo4.mapper.IEmpleadoMapper;
import com.oo2.grupo4.repositories.IEmpleadoRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.IEmpleadoService;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.mapper.ITicketMapper;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EmpleadoService implements IEmpleadoService {

	private final PersonaService personaService;
	private final AreaService areaService;
	private final IEmpleadoRepository empleadoRepository;
	private final ITicketRepository ticketRepository;
    private final IEmpleadoMapper empleadoMapper;
    private final ITicketMapper ticketMapper;
	

    //METODOS PARA MODELANDVIEW
    
	@Override
	public Empleado crearEmpleado(String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol) {
		personaService.validarDniNoExiste(dni);

		Area area = areaService.traerPorId(idArea);

		Empleado empleado = new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDni(dni);
		empleado.setLegajo(legajo);
		empleado.setRol(rol);
		empleado.setArea(area);

		return empleadoRepository.save(empleado);
	}

	@Override
	public int modificarEmpleado(int idPersona, String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol) {
		
		Empleado empleado = this.traerPorId(idPersona);
		Area area = areaService.traerPorId(idArea);

		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDni(dni);
		empleado.setLegajo(legajo);
		empleado.setArea(area);
		empleado.setRol(rol);

		return empleadoRepository.save(empleado).getIdPersona();
	}

	//METODS PARA REST
	
    public Empleado crearEmpleado(EmpleadoCreateDTO dto) {
        personaService.validarDniNoExiste(dto.dni());
        Empleado empleado = empleadoMapper.toEntity(dto);
        empleado.setArea(areaService.traerPorId(dto.idArea()));
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizarEmpleado(EmpleadoUpdateDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.idPersona())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setNombre(dto.nombre());
        empleado.setApellido(dto.apellido());
        empleado.setDni(dto.dni());
        empleado.setLegajo(dto.legajo());
        empleado.setRol(dto.rol());
        empleado.setArea(areaService.traerPorId(dto.idArea()));

        return empleadoRepository.save(empleado);
    }

    public List<EmpleadoDTO> getAllDTOs() {
        return empleadoRepository.findAll()
                .stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO getDTOById(int id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return empleadoMapper.toDTO(empleado);
    }
    
    // METODOS COMPARTIDOS 
    
	@Override
	public List<Empleado> obtenerEmpleadosPorArea(int idArea) {
		return empleadoRepository.findByAreaIdArea(idArea);
	}

	@Override 
	public List <Empleado> traerNoAdmi (String rol){
	    if (rol.equalsIgnoreCase("admin")) {
	        return empleadoRepository.findByRolNot("ADMIN");
	    }
	    return empleadoRepository.findByRol(rol);
	}
	
	@Override
	public Empleado traerPorId(int idPersona) {
		return empleadoRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id " + idPersona));
	}
	
	

@Override
public List<TicketResponseDTO> getAllByEmpleadoId(int idEmpleado) {
	return ticketRepository.findAll().stream()
		.filter(t -> t.getEmpleado() != null && t.getEmpleado().getIdPersona() == idEmpleado)
		.map(ticketMapper::toDTO)
		.collect(Collectors.toList());
}


	@Override
	public void delete(int idPersona) {
		empleadoRepository.deleteById(idPersona);
	}
	
	@Override
	public List<Empleado> getAll() {
		return empleadoRepository.findAll();
	}
	
}