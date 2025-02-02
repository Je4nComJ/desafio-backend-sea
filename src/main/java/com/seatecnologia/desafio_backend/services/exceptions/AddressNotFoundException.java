package com.seatecnologia.desafio_backend.services.exceptions;

public class AddressNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AddressNotFoundException(String mensagem) {
		super(mensagem);
	}

}
