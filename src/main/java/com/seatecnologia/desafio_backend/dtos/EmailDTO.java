package com.seatecnologia.desafio_backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {
	
	private Long id;
	
	@NotBlank
	@Email(message = "O email deve estar em formato válido")
	private String enderecoEmail;
	
	public EmailDTO() {}
	
	public EmailDTO(com.seatecnologia.desafio_backend.entities.Email entity) {
		id = entity.getId();
		enderecoEmail = entity.getEnderecoEmail();
	}
}
