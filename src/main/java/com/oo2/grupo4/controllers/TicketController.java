package com.oo2.grupo4.controllers;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.exceptions.DescripcionMuyCortaException;
import com.oo2.grupo4.exceptions.TicketYaExistente;
import com.oo2.grupo4.security.UserDetailsImpl;
import com.oo2.grupo4.dto.TicketCreateDTO;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Ticket;

import com.oo2.grupo4.services.implementation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TicketController {

	private final EmpleadoService empleadoService;

	private final TipoDeTicketService tipodeticketservice;
	private final EstadoService estadoService;
	private final TicketService ticketService;
	private final EmailService emailService;
	private final ClienteService clienteService;

	@GetMapping("/crearTicket")
	public ModelAndView vistaCrearTicket(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("tickets/crearTicket");
		mav.addObject("error", mensaje);
		mav.addObject("ticket", new TicketCreateDTO("", "", 0, 0));

		mav.addObject("estados", estadoService.getAll());
		mav.addObject("tipoDeTickets", tipodeticketservice.getAll());

		return mav;
	}

	@PostMapping("/crearTicket")
	public ModelAndView vistaCrearTicket(@ModelAttribute("ticket") TicketCreateDTO dto,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ModelAndView mav = new ModelAndView("tickets/crearTicket");

		try {
			if (!ticketService.existsByTitulo(dto.titulo())) {

				// CREACION DE TICKET

				int idPersona = userDetails.getLogin().getPersona().getIdPersona();
				TicketCreateDTO nuevoDto = new TicketCreateDTO(dto.titulo(), dto.descripcion(), dto.idTipoDeTicket(),
						idPersona);

				Ticket ticket = ticketService.crearTicket(nuevoDto);

				// CORREO

				String destinatario = userDetails.getLogin().getCorreo();
				String asunto = "[Ticket #" + ticket.getIdTicket() + "] ✅" + dto.titulo();
				String cuerpo = "👌 Has creado con exito un nuevo ticket:\n\nTipo de ticket: "
						+ ticket.getTipoDeTicket().getTipo() + "\nDescripción: " + dto.descripcion();
				emailService.enviarConfirmacionTicket(destinatario, asunto, cuerpo);

				return new ModelAndView("redirect:/mail/mailEnvio");
			} else {
				throw new TicketYaExistente("El ticket con el título: " + dto.titulo() + " ya existe");
			}
		} catch (DescripcionMuyCortaException e) {
			return new ModelAndView("error/descripcionMuycorta");
		}

	}

	@GetMapping("/listaTickets")
	public ModelAndView vistaListaTickets(@RequestParam(required = false) String mensaje) {

		ModelAndView mav = new ModelAndView("tickets/listaTickets");

		mav.addObject("tickets", ticketService.mostrarTickets());

		return mav;
	}

	@GetMapping("/misTickets")
	public ModelAndView vistaListaTickets(@RequestParam(required = false) String mensaje,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		int idPersona = userDetails.getLogin().getPersona().getIdPersona();

		ModelAndView mav = new ModelAndView("tickets/listaTickets");

		mav.addObject("tickets", clienteService.getAllByClienteId(idPersona));

		return mav;
	}

	@GetMapping("/misTicketsEmpleado")
	public ModelAndView vistaListaTicketsEmpleado(@RequestParam(required = false) String mensaje,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		int idPersona = userDetails.getLogin().getPersona().getIdPersona();

		ModelAndView mav = new ModelAndView("tickets/listaTickets");

		mav.addObject("tickets", empleadoService.getAllByEmpleadoId(idPersona));

		return mav;
	}

	@GetMapping("/mail/mailEnvio")
	public ModelAndView vistaMailEnviado() {
		return new ModelAndView("mail/mailEnvio");
	}

	@GetMapping("/verTicket")
	public ModelAndView verTicket(@RequestParam int idTicket) {
		ModelAndView mav = new ModelAndView("tickets/verTicket");
		Ticket ticket = ticketService.getById(idTicket);

		if (ticket == null) {
			mav.setViewName("redirect:/listaTickets");
			mav.addObject("mensaje", "El ticket no existe.");
			return mav;
		}

		mav.addObject("ticket", ticketService.convertirADTO(ticket));
		return mav;
	}

	@PostMapping("/eliminarTicket")
	public ModelAndView eliminarTicket(@RequestParam int idTicket) {

		ModelAndView mav = new ModelAndView("tickets/listaTickets");

		ticketService.delete(idTicket);

		mav.addObject("tickets", ticketService.mostrarTickets());

		return mav;

	}

}
