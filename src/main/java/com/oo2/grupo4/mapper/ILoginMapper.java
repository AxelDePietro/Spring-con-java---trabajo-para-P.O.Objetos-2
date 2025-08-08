package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oo2.grupo4.dto.LoginCreateDTO;
import com.oo2.grupo4.entities.Login;

@Mapper(componentModel = "spring")
public interface ILoginMapper {

	@Mapping(target = "idLogin", ignore = true)
	@Mapping(target = "persona", ignore = true)
	Login toEntity (LoginCreateDTO loginCreateDTO);

}