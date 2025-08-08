package com.oo2.grupo4.exceptions;

public class TicketSinActualizaciones extends RuntimeException {

	public TicketSinActualizaciones(int idTicket) {
		super("el ticket " + idTicket + " no tiene actualizaciones");
	}
	
}
