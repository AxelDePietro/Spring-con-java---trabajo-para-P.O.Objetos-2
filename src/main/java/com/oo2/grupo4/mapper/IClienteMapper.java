package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oo2.grupo4.dto.ClienteCreateDTO;
import com.oo2.grupo4.dto.ClienteResponseDTO;
import com.oo2.grupo4.entities.Cliente;

@Mapper(componentModel = "spring")
public interface IClienteMapper {
	
	Cliente toEntity(ClienteCreateDTO dto); 
	
	@Mapping(target = "idPersona", source = "idPersona")
	ClienteResponseDTO toDTO(Cliente cliente);

}
