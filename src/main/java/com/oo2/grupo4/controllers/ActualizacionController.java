package com.oo2.grupo4.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.security.UserDetailsImpl;
import com.oo2.grupo4.services.implementation.ActualizacionService;
import com.oo2.grupo4.services.implementation.ContactoService;
import com.oo2.grupo4.services.implementation.EmpleadoService;
import com.oo2.grupo4.services.implementation.PersonaService;
import com.oo2.grupo4.services.implementation.TicketService;

import lombok.RequiredArgsConstructor;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class ActualizacionController {

	private final AdminController adminController;

	private final ContactoService contactoService;

	private final ActualizacionService actualizacionService;
	private final EmpleadoService empleadoService;
	private final PersonaService personaService;
	private final TicketService ticketService;

	@GetMapping("/crearActualizacion")
	public ModelAndView crearActualizacionVista(@RequestParam int idTicket) {
		
		ModelAndView mav = new ModelAndView("actualizacion/crearActualizacion");
		mav.addObject("empleados", empleadoService.getAll());
		mav.addObject("actualizacion", new ActualizacionCreateDTO("", 0, idTicket));
		mav.addObject("idTicket", idTicket);
		return mav;
	}

//	@PostMapping("/crearActualizacion")
//	public ModelAndView crearActualizacion(@RequestParam int idTicket, @RequestParam String contenido,
//			@AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
//
//		Ticket ticket = ticketService.getById(idTicket);
//
//		Actualizacion actualizacion = new Actualizacion();
//		actualizacion.setTicket(ticket);
//		actualizacion.setContenido(contenido);
//		actualizacion.setFechaActualizacion(LocalDateTime.now());
//		actualizacion.setEmpleado(ticket.getEmpleado());
//		actualizacionService.save(actualizacion);
//
//		mav.addObject("actualizaciones", actualizacionService.getAllByTicketId(idTicket));
//		mav.addObject("idTicket", idTicket);
//		mav.addObject("empleado", ticket.getEmpleado());
//
//		return mav;
//
//	}

	@PostMapping("/crearActualizacion")
	public ModelAndView crearActualizacion(@RequestParam int idTicket, @ModelAttribute("actualizacion") ActualizacionCreateDTO dto,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		int idPersona = userDetails.getLogin().getPersona().getIdPersona();
		
		ActualizacionCreateDTO dtoNuevo = new ActualizacionCreateDTO(dto.contenido(), idPersona, idTicket);
		
		actualizacionService.crearActualizacion(dtoNuevo);
		
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		mav.addObject("actualizaciones", actualizacionService.listaActualizacionesDTOporTicket(idTicket));
		mav.addObject("idTicket", idTicket);
		return mav;
	}

//	@GetMapping("/listaActualizaciones")
//	public ModelAndView listaActualizaciones(@RequestParam int idTicket) {
//		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
//
//		mav.addObject("actualizaciones", actualizacionService.getAllByTicketId(idTicket));
//		mav.addObject("idTicket", idTicket);
//
//		return mav;
//	}

	@GetMapping("/listaActualizaciones")
	public ModelAndView listaActualizaciones(@RequestParam int idTicket) {
		
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		mav.addObject("actualizaciones", actualizacionService.listaActualizacionesDTOporTicket(idTicket));
		mav.addObject("idTicket", idTicket);
		return mav;
	}

	@PostMapping("/eliminarActualizacion")
	public ModelAndView eliminarActualizacion(@RequestParam int idTicket, @RequestParam int idActualizacion) {
		
		actualizacionService.delete(idTicket, idActualizacion);
		
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		mav.addObject("actualizaciones", actualizacionService.listaActualizacionesDTOporTicket(idTicket));
		mav.addObject("idTicket", idTicket);
		return mav;
	}

}
