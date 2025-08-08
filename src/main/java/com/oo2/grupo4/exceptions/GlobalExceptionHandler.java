package com.oo2.grupo4.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TicketYaExistente.class)
	public String manejearTicketYaExistente(TicketYaExistente ex, Model model) {
		model.addAttribute("mensaje", ex.getMessage());
		return "error/ticketYaExistente";
	}
	
	@ExceptionHandler(MailExistente.class)
	public String manejarMailExistente(MailExistente ex, Model model) {
		model.addAttribute("mensaje", ex.getMessage());
		return "error/mailExistente";
	}
	
	@ExceptionHandler(DniExistente.class)
	public String manejarDniExistente(DniExistente ex, Model model) {
		model.addAttribute("mensaje", ex.getMessage());
		return "error/dniExistente";
	}
	
	@ExceptionHandler(TicketSinActualizaciones.class)
	public String manejarTicketSinActualizaciones(TicketSinActualizaciones ex, Model model) {
		model.addAttribute("mensaje", ex.getMessage());
		return "error/ticketSinActualizaciones";
	}
	
}
