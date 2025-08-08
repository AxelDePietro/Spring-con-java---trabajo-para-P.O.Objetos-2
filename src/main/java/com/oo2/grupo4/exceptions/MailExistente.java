package com.oo2.grupo4.exceptions;

public class MailExistente extends RuntimeException {
	
	public MailExistente(String mensaje) {
		super(mensaje);
	}

}
