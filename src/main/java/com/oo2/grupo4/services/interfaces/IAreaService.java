package com.oo2.grupo4.services.interfaces;

import java.util.List;
import com.oo2.grupo4.entities.Area;


public interface IAreaService {

	List<Area> traerTodas();

	Area traerPorId(int idArea);
	
	Area traerPorNombre (String nombre);
}