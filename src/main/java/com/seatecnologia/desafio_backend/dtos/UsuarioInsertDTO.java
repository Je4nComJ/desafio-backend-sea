package com.seatecnologia.desafio_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInsertDTO extends UsuarioDTO {
	
	private String password;
	
	public UsuarioInsertDTO() {
		super();
	}
}
